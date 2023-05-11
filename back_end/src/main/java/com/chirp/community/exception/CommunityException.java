package com.chirp.community.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor @Getter
public class CommunityException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String descriptionForClient;
    private final String descriptionForServer;

    public static CommunityException of(HttpStatus httpStatus, String descriptionForClient, String descriptionForServer) {
        return new CommunityException(httpStatus, descriptionForClient, descriptionForServer);
    }
    public static CommunityException of(HttpStatus httpStatus, String description) {
        return new CommunityException(httpStatus, description, description);
    }
}
