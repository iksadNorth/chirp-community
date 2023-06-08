package com.chirp.community.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder(toBuilder = true)
public record KeyReadResponse(
        @Schema(
                description = "공개키",
                example = "aucjefjwjdujeksifjw"
        )
        String key
) {
    public static KeyReadResponse of(String key) {
        return KeyReadResponse.builder()
                .key(key)
                .build();
    }
}
