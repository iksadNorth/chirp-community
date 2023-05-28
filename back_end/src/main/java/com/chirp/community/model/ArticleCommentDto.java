package com.chirp.community.model;

import com.chirp.community.entity.ArticleComment;
import com.chirp.community.entity.SiteUser;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Optional;

@Builder(toBuilder = true)
public record ArticleCommentDto(
        Long id,
        LocalDateTime createdAt,
        String content,
        ArticleDto article,
        SiteUserDto writer
) {

    // null 고려
    public static ArticleCommentDto fromEntity(ArticleComment entity) {
        ArticleDto article = Optional.ofNullable(entity.getArticle())
                .map(ArticleDto::fromEntity)
                .orElse(null);
        SiteUserDto writer = Optional.ofNullable(entity.getWriter())
                .map(SiteUserDto::fromEntity)
                .orElse(null);
        return ArticleCommentDto.builder()
                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .content(entity.getContent())
                .article(article)
                .writer(writer)
                .build();

    }

}
