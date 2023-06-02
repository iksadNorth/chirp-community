package com.chirp.community.model.response;

import com.chirp.community.model.ArticleDto;
import com.chirp.community.model.BoardDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
public record ArticleReadResponse(
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
                description = "게시물 내용",
                example = "이번 3.0 패치에서는 버프가 주된 내용이다."
        )
        String content,
        @Schema(
                description = "게시물이 속한 게시판"
        )
        BoardDto board,
        @Schema(
                description = "게시물을 작성한 이용자"
        )
        SiteUserReadResponse writer,
        @Schema(
                description = "게시물 조회수",
                example = "42"
        )
        Long views
) {
    public static ArticleReadResponse of(ArticleDto dto) {
        return ArticleReadResponse.builder()
                .id(dto.id())
                .createdAt(dto.createdAt())
                .title(dto.title())
                .content(dto.content())
                .board((dto.board()))
                .writer(SiteUserReadResponse.of(dto.writer()))
                .views(dto.views())
                .build();
    }
}
