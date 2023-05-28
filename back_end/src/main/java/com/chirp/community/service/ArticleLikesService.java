package com.chirp.community.service;

import com.chirp.community.model.LikesDto;

public interface ArticleLikesService {
    LikesDto readLikes(Long id);

    LikesDto toggleLikes(Long articleId);

    LikesDto toggleDisLikes(Long articleId);
}
