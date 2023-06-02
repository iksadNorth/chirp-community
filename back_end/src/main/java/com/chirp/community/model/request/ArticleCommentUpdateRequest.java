package com.chirp.community.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder(toBuilder = true)
public record ArticleCommentUpdateRequest(
        @Schema(
                description = "댓글 내용",
                example = "유용한 정보글 ㅊㅊ."
        )
        String content
) { }
