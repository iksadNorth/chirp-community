package com.chirp.community.model;

import com.chirp.community.type.LikeType;
import lombok.Builder;

@Builder(toBuilder = true)
public record LikesDto(
        long numLikes,
        LikeType likeType
) {
}
