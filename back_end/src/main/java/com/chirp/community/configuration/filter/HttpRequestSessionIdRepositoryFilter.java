package com.chirp.community.configuration.filter;

import jakarta.servlet.ServletRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class HttpRequestSessionIdRepositoryFilter extends SessionIdRepositoryFilter {
    public HttpRequestSessionIdRepositoryFilter() {
        super(new ThreadLocal<>());
    }

    @Override
    protected String makeSessionId(ServletRequest request) {
        String httpRequestSessionId = UUID.randomUUID().toString();
        return httpRequestSessionId;
    }
}
