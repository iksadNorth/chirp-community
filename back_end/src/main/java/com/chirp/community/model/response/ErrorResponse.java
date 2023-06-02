package com.chirp.community.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder(toBuilder = true)
public record ErrorResponse(
        @Schema(
                description = """
                        사용자에게 노출되어도 괜찮은 평문 에러 코드.
                        """,
                example = "해당 계정은 존재하지 않습니다."
        )
        String errorMessage
) {
    public static ErrorResponse of(String errorMessage) {
        return ErrorResponse.builder()
                .errorMessage(errorMessage)
                .build();
    }
}
