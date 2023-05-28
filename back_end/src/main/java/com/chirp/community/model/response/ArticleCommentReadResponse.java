package com.chirp.community.model.response;

import com.chirp.community.model.ArticleCommentDto;
import com.chirp.community.model.ArticleDto;
import com.chirp.community.model.BoardDto;
import com.chirp.community.model.SiteUserDto;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
public record ArticleCommentReadResponse(
        Long id,
        LocalDateTime createdAt,
        String content,
        ArticleDto article,
        SiteUserReadResponse writer
) {
    public static ArticleCommentReadResponse of(ArticleCommentDto dto) {
        return ArticleCommentReadResponse.builder()
                .id(dto.id())
                .createdAt(dto.createdAt())
                .content(dto.content())
                .article(dto.article())
                .writer(SiteUserReadResponse.of(dto.writer()))
                .build();
    }
}
