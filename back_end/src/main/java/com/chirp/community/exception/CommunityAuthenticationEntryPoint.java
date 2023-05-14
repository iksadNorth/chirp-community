package com.chirp.community.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
public class CommunityAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        String errorMessage = "인증 오류 발생.";
        log.warn(authException.getMessage());

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        try (OutputStream os = response.getOutputStream()) {
            os.write(errorMessage.getBytes(StandardCharsets.UTF_8));
            os.flush();
        }
    }
}
