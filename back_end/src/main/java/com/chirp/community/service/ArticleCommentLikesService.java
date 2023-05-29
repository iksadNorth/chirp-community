package com.chirp.community.service;

import com.chirp.community.model.LikesDto;

public interface ArticleCommentLikesService {
    LikesDto readLikes(Long id);

    LikesDto toggleLikes(Long id);

    LikesDto toggleDisLikes(Long id);
}
