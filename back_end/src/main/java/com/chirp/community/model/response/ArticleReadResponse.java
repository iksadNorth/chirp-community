package com.chirp.community.model.response;

import com.chirp.community.model.ArticleDto;
import com.chirp.community.model.BoardDto;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
public record ArticleReadResponse(
        Long id,
        LocalDateTime createdAt,
        String title,
        String content,
        BoardDto board,
        SiteUserReadResponse writer,
        Long views
) {
    public static ArticleReadResponse of(ArticleDto dto) {
        return ArticleReadResponse.builder()
                .id(dto.id())
                .createdAt(dto.createdAt())
                .title(dto.title())
                .content(dto.content())
                .board((dto.board()))
                .writer(SiteUserReadResponse.of(dto.writer()))
                .views(dto.views())
                .build();
    }
}
