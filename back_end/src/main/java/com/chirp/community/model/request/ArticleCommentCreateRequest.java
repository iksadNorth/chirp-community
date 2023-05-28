package com.chirp.community.model.request;

import lombok.Builder;

@Builder(toBuilder = true)
public record ArticleCommentCreateRequest(
        String content,
        Long article_id
) { }
