package com.chirp.community.model.response;

import com.chirp.community.model.ArticleCommentDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
public record ArticleCommentReadRecentlyResponse(
        @Schema(
                description = "댓글 ID",
                example = "4245652"
        )
        Long id,
        @Schema(
                description = "댓글 생성일",
                example = "1970-01-01T00:00:00.000+00:00"
        )
        LocalDateTime createdAt,
        @Schema(
                description = "댓글 내용",
                example = "유용한 정보글 ㅊㅊ."
        )
        String content,
        @Schema(
                description = "댓글이 달린 게시물 ID",
                example = "526"
        )
        Long articleId
) {
    public static ArticleCommentReadRecentlyResponse of(ArticleCommentDto dto) {
        return ArticleCommentReadRecentlyResponse.builder()
                .id(dto.id())
                .createdAt(dto.createdAt())
                .content(dto.content())
                .articleId(dto.article().id())
                .build();
    }
}
