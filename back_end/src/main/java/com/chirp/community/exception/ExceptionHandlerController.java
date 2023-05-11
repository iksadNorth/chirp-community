package com.chirp.community.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(CommunityException.class)
    public ResponseEntity<String> handleCommunityException(CommunityException e) {
        log.warn(e.getDescriptionForServer());
        return new ResponseEntity<>(e.getDescriptionForClient(), e.getHttpStatus());
    }
}
