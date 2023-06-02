package com.chirp.community.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder(toBuilder = true)
public record BoardUpdateRequest (
        @Schema(
                description = "게시판 이름",
                example = "League Of Legend"
        )
        String name
) {}
