package com.chirp.community.model;

import com.chirp.community.entity.ArticleComment;
import com.chirp.community.entity.SiteUser;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
public record ArticleCommentDto(
        Long id,
        LocalDateTime createdAt,
        String content,
        ArticleDto article,
        SiteUserDto writer
) {

    public static ArticleCommentDto fromEntity(ArticleComment entity) {
        return ArticleCommentDto.builder()
                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .content(entity.getContent())
                .article(ArticleDto.fromEntity(entity.getArticle()))
                .writer(SiteUserDto.fromEntity(entity.getWriter()))
                .build();

    }

}
