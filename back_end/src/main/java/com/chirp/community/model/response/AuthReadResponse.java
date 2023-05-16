package com.chirp.community.model.response;

import lombok.Builder;

@Builder(toBuilder = true)
public record AuthReadResponse(
        String token
) {
    public static AuthReadResponse of(String token) {
        return AuthReadResponse.builder()
                .token(token)
                .build();
    }
}
