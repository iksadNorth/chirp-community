package com.chirp.community.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder(toBuilder = true)
public record ArticleCommentCreateRequest(
        @Schema(
                description = "댓글 내용",
                example = "유용한 정보글 ㅊㅊ."
        )
        String content,
        @Schema(
                description = "댓글이 쓰인 게시글 ID",
                example = "4245652"
        )
        Long article_id
) { }
