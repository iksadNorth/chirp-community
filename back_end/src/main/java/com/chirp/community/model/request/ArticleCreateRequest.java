package com.chirp.community.model.request;

import lombok.Builder;

@Builder(toBuilder = true)
public record ArticleCreateRequest (
        String title,
        String content,
        Long board_id
) {}
