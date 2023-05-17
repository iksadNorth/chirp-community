package com.chirp.community.model.response;

import lombok.Builder;

@Builder(toBuilder = true)
public record ErrorResponse(
        String errorMessage
) {
    public static ErrorResponse of(String errorMessage) {
        return ErrorResponse.builder()
                .errorMessage(errorMessage)
                .build();
    }
}
