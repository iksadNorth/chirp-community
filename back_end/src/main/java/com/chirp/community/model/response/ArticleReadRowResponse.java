package com.chirp.community.model.response;

import com.chirp.community.model.ArticleDto;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
public record ArticleReadRowResponse(
        Long id,
        LocalDateTime createdAt,
        String title,
        SiteUserReadRowResponse writer,
        Long views
) {
    public static ArticleReadRowResponse of(ArticleDto dto) {
        return ArticleReadRowResponse.builder()
                .id(dto.id())
                .createdAt(dto.createdAt())
                .title(dto.title())
                .writer(SiteUserReadRowResponse.of(dto.writer()))
                .views(dto.views())
                .build();
    }
}
