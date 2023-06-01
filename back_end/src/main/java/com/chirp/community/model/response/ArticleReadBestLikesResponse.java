package com.chirp.community.model.response;

import com.chirp.community.model.ArticleDto;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
public record ArticleReadBestLikesResponse(
        Long id,
        LocalDateTime createdAt,
        String title,
        BoardReadResponse board,
        Long numLikes
) {
    public static ArticleReadBestLikesResponse of(ArticleDto dto) {
        return ArticleReadBestLikesResponse.builder()
                .id(dto.id())
                .createdAt(dto.createdAt())
                .title(dto.title())
                .board(BoardReadResponse.of(dto.board()))
                .numLikes(dto.numLikes())
                .build();
    }
}
