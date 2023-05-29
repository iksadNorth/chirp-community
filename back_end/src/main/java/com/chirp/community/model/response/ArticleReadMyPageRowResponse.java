package com.chirp.community.model.response;

import com.chirp.community.model.ArticleDto;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
public record ArticleReadMyPageRowResponse(
        Long id,
        LocalDateTime createdAt,
        String title,
        BoardReadResponse board,
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
