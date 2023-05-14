package com.chirp.community.model.request;

import lombok.Builder;

@Builder(toBuilder = true)
public record ArticleUpdateRequest (
        Long id,
        String title,
        String content
) {}
