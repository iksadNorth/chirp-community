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
        Long views,
        Long numLikes
) {
    public static ArticleDto fromEntity(Article entity) {
        return ArticleDto.builder()
                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .title(entity.getTitle())
                .content(entity.getContent())
                .views(entity.getViews())
                .build();
    }
}
