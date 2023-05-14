package com.chirp.community.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
public class CommunityAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        String errorMessage = "인가 오류 발생.";
        log.warn(accessDeniedException.getMessage());

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        try (OutputStream os = response.getOutputStream()) {
            os.write(errorMessage.getBytes(StandardCharsets.UTF_8));
            os.flush();
        }
    }
}
