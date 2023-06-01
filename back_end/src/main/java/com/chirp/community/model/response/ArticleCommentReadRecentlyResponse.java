package com.chirp.community.model.response;

import com.chirp.community.model.ArticleCommentDto;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
public record ArticleCommentReadRecentlyResponse(
        Long id,
        LocalDateTime createdAt,
        String content
) {
    public static ArticleCommentReadRecentlyResponse of(ArticleCommentDto dto) {
        return ArticleCommentReadRecentlyResponse.builder()
                .id(dto.id())
                .createdAt(dto.createdAt())
                .content(dto.content())
                .build();
    }
}
