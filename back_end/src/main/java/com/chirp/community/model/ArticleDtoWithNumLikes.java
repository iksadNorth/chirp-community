package com.chirp.community.model;

import com.chirp.community.entity.Article;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
public record ArticleDtoWithNumLikes(
        Long id,
        LocalDateTime createdAt,
        String title,
        String content,
        BoardDto board,
        SiteUserDto writer,
        Long views,
        Long numLikes
) {
    public static ArticleDtoWithNumLikes fromEntity(Article entity) {
        return ArticleDtoWithNumLikes.builder()
                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .title(entity.getTitle())
                .content(entity.getContent())
                .views(entity.getViews())
                .build();
    }
}
