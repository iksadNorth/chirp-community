package com.chirp.community.configuration.filter;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class PageSessionIdRepositoryFilter extends SessionIdRepositoryFilter {
    private static final String HEADER_PAGE_SESSION_ID = "page-session-id";

    public PageSessionIdRepositoryFilter() {
        super(new ThreadLocal<>());
    }

    @Override
    protected String makeSessionId(ServletRequest request) {
        HttpServletRequest req = (HttpServletRequest) request;

        String header = req.getHeader(HEADER_PAGE_SESSION_ID);
        return header;
    }
}
