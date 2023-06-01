package com.chirp.community.model.response;

import com.chirp.community.model.ArticleDto;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
public record ArticleReadRecentlyResponse(
        Long id,
        LocalDateTime createdAt,
        String title,
        String content,
        Long views
) {
    public static ArticleReadRecentlyResponse of(ArticleDto dto) {
        return ArticleReadRecentlyResponse.builder()
                .id(dto.id())
                .createdAt(dto.createdAt())
                .title(dto.title())
                .content(dto.content())
                .views(dto.views())
                .build();
    }
}
