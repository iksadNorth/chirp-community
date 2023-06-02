package com.chirp.community.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder(toBuilder = true)
public record ArticleUpdateRequest (
        @Schema(
                description = "게시물 ID",
                example = "1653452"
        )
        Long id,
        @Schema(
                description = "게시물 제목",
                example = "새로운 패치 내용 정리"
        )
        String title,
        @Schema(
                description = "게시물 내용",
                example = "이번 3.0 패치에서는 버프가 주된 내용이다."
        )
        String content
) {}
