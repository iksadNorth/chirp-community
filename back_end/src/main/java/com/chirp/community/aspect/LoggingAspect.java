package com.chirp.community.aspect;

import com.chirp.community.configuration.filter.HttpRequestSessionIdRepositoryFilter;
import com.chirp.community.configuration.filter.PageSessionIdRepositoryFilter;
import com.chirp.community.utils.LogUtils;
import com.chirp.community.utils.SessionIdLogUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;

import static com.chirp.community.utils.LogUtils.makeLoggingMessage;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {
    private final PageSessionIdRepositoryFilter pageSessionIdRepositoryFilter;
    private final HttpRequestSessionIdRepositoryFilter httpRequestSessionIdRepositoryFilter;

    @Around(
            "execution(* com.chirp.community.repository.*.*(..)) && " +
                    "!execution(* com.chirp.community.repository.*Impl.*(..))"
    )
    public Object logRepository(ProceedingJoinPoint joinPoint) throws Throwable {
        LogLevel level = LogLevel.TRACE;

        if (LogUtils.LogLevelOf(log).equals(level)) {
            return returnWithLogs(joinPoint)
                    .doWithMessage(table -> {
                        String message = makeLoggingMessage(table);
                        LogUtils.logWhenLoggingLevelIs(log, level, message);
                    });
        } else {
            return joinPoint.proceed();
        }
    }

    public SessionIdLogUtils.SessionIdResult<Object> returnWithLogs(ProceedingJoinPoint joinPoint) {
        SessionIdLogUtils.SessionIdDto dto = SessionIdLogUtils.SessionIdDto.builder()
                .nameClassLogging(joinPoint.getSignature().getDeclaringType().getSimpleName())
                .nameMethodLogging(joinPoint.getSignature().getName())
                .pageSessionId(pageSessionIdRepositoryFilter.getSessionId())
                .reqSessionId(httpRequestSessionIdRepositoryFilter.getSessionId())
                .build();

        // 로깅 Map 생성.
        SessionIdLogUtils.SessionIdResult<Object> sessionIdResult = SessionIdLogUtils.returnWithLogs(dto, () -> {
            try {
                return joinPoint.proceed();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        });

        // Repository 접근마다 카운트.
        httpRequestSessionIdRepositoryFilter.countNumCalls();

        // 실제 로깅이 이뤄지는 곳.
        return sessionIdResult;
    }
}
