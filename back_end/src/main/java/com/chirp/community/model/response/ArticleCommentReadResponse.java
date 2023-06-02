package com.chirp.community.model.response;

import com.chirp.community.model.ArticleCommentDto;
import com.chirp.community.model.ArticleDto;
import com.chirp.community.model.BoardDto;
import com.chirp.community.model.SiteUserDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
public record ArticleCommentReadResponse(
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
                description = "댓글이 작성된 게시글"
        )
        ArticleDto article,
        @Schema(
                description = "댓글을 작성한 이용자"
        )
        SiteUserReadResponse writer
) {
    public static ArticleCommentReadResponse of(ArticleCommentDto dto) {
        return ArticleCommentReadResponse.builder()
                .id(dto.id())
                .createdAt(dto.createdAt())
                .content(dto.content())
                .article(dto.article())
                .writer(SiteUserReadResponse.of(dto.writer()))
                .build();
    }
}
