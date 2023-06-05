package com.chirp.community.configuration.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class SessionIdRepositoryFilter extends OncePerRequestFilter {
    @Builder(toBuilder = true)
    public record SessionLogDto(
            String sessionId,
            int numCalls
    ) {}

    private final ThreadLocal<SessionLogDto> sessionIdThreadLocal;

    private Optional<SessionLogDto> getSessionLogDto() {
        return Optional.ofNullable(sessionIdThreadLocal.get());
    }

    protected abstract String makeSessionId(ServletRequest request);

    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 세션값을 ThreadLocal에 저장하기.
        String sessionId = makeSessionId(request);
        setSessionId(sessionId);

        // 코드 실행.
        chain.doFilter(request, response);

        // 메모리 정리.
        sessionIdThreadLocal.remove();
    }

    public String getSessionId() {
        return getSessionLogDto()
                .map(SessionLogDto::sessionId)
                .orElse(null);
    }

    private void setSessionId(String sessionId) {
        sessionIdThreadLocal.set(
                SessionLogDto.builder()
                        .sessionId(sessionId)
                        .numCalls(0)
                        .build()
        );
    }

    public String getNumCalls() {
        return getSessionLogDto()
                .map(SessionLogDto::numCalls)
                .map(String::valueOf)
                .orElse(null);
    }

    public void countNumCalls() {
        getSessionLogDto().ifPresent(logDto -> {
            sessionIdThreadLocal.set(
                    logDto.toBuilder()
                            .numCalls(logDto.numCalls() + 1)
                            .build()
                    );
            });
    }
}
