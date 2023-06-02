package com.chirp.community.model.response;

import com.chirp.community.model.ArticleDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
public record ArticleReadMyPageRowResponse(
        @Schema(
                description = "게시물 ID",
                example = "1653452"
        )
        Long id,
        @Schema(
                description = "게시물 생성일",
                example = "1970-01-01T00:00:00.000+00:00"
        )
        LocalDateTime createdAt,
        @Schema(
                description = "게시물 제목",
                example = "새로운 패치 내용 정리"
        )
        String title,
        @Schema(
                description = "게시글이 속한 게시판"
        )
        BoardReadResponse board,
        @Schema(
                description = "게시물 조회수",
                example = "42"
        )
        Long views
) {
    public static ArticleReadMyPageRowResponse of(ArticleDto dto) {
        return ArticleReadMyPageRowResponse.builder()
                .id(dto.id())
                .createdAt(dto.createdAt())
                .title(dto.title())
                .board(BoardReadResponse.of(dto.board()))
                .views(dto.views())
                .build();
    }
}
