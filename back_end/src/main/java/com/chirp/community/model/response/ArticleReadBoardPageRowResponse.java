package com.chirp.community.model.response;

import com.chirp.community.model.ArticleDto;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
public record ArticleReadBoardPageRowResponse(
        Long id,
        LocalDateTime createdAt,
        String title,
        SiteUserReadRowResponse writer,
        Long views
) {
    public static ArticleReadBoardPageRowResponse of(ArticleDto dto) {
        return ArticleReadBoardPageRowResponse.builder()
                .id(dto.id())
                .createdAt(dto.createdAt())
                .title(dto.title())
                .writer(SiteUserReadRowResponse.of(dto.writer()))
                .views(dto.views())
                .build();
    }
}
