package com.chirp.community.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder(toBuilder = true)
public record AuthRequest(
        @Schema(
                description = "email 주소",
                example = "anyEmail@gmail.com"
        )
        String email,
        @Schema(
                description = "password",
                example = "q1w2e3r4"
        )
        String password
) {
}
