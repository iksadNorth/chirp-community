package com.chirp.community.configuration.filter;

import com.chirp.community.utils.LogUtils;
import com.chirp.community.utils.SessionIdLogUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.chirp.community.utils.LogUtils.makeLoggingMessage;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoggingFilter extends OncePerRequestFilter {
    private final PageSessionIdRepositoryFilter pageSessionIdRepositoryFilter;
    private final HttpRequestSessionIdRepositoryFilter httpRequestSessionIdRepositoryFilter;
    private final LogLevel level = LogLevel.TRACE;

    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (LogUtils.LogLevelOf(log).equals(level)) {
            returnWithLogs(request, response, chain)
                    .doWithMessage(table -> {
                        String message = makeLoggingMessage(table);
                        LogUtils.logWhenLoggingLevelIs(log, level, message);
                    });
        } else {
            chain.doFilter(request, response);
        }
    }

    public SessionIdLogUtils.SessionIdResult<Void> returnWithLogs(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
        SessionIdLogUtils.SessionIdDto dto = SessionIdLogUtils.SessionIdDto.builder()
                .nameClassLogging(getClass().getSimpleName())
                .nameMethodLogging("doFilterInternal")
                .pageSessionId(pageSessionIdRepositoryFilter.getSessionId())
                .reqSessionId(httpRequestSessionIdRepositoryFilter.getSessionId())
                .build();

        // 로깅 Map 생성.
        SessionIdLogUtils.SessionIdResult<Void> sessionIdResult = SessionIdLogUtils.returnWithLogs(dto, () -> {
            try {
                chain.doFilter(request, response);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        });

        // 1개의 요청당 호출한 DB 쿼리 갯수 로깅.
        String numCalls = httpRequestSessionIdRepositoryFilter.getNumCalls();

        // 로깅 Map return.
        return sessionIdResult
                .put("current-page-url", request.getHeader("current-page-url"))
                .put("request-uri", request.getRequestURI())
                .put("num-calls-of-query", numCalls);
    }
}
