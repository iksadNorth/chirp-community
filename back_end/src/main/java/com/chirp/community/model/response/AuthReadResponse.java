package com.chirp.community.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder(toBuilder = true)
public record AuthReadResponse(
        @Schema(
                description = "JWT 토큰",
                example = "somejwtheader.somejwtpayload.somejwtsignature"
        )
        String token
) {
    public static AuthReadResponse of(String token) {
        return AuthReadResponse.builder()
                .token(token)
                .build();
    }
}
