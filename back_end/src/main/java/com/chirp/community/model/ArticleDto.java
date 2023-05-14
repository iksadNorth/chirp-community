package com.chirp.community.model;

import com.chirp.community.entity.Article;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Optional;

@Builder(toBuilder = true)
public record ArticleDto(
        Long id,
        LocalDateTime createdAt,
        String title,
        String content,
        BoardDto board,
        SiteUserDto writer,
        Long views
) {
    public static ArticleDto fromEntity(Article entity) {
        BoardDto board = Optional.ofNullable(entity.getBoard())
                .map(BoardDto::fromEntity)
                .orElse(null);
        SiteUserDto writer = Optional.ofNullable(entity.getWriter())
                .map(SiteUserDto::fromEntity)
                .orElse(null);
        return ArticleDto.builder()
                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .title(entity.getTitle())
                .content(entity.getContent())
                .board(board)
                .writer(writer)
                .views(entity.getViews())
                .build();
    }
}
