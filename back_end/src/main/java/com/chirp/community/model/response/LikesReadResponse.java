package com.chirp.community.model.response;

import com.chirp.community.type.LikeType;
import lombok.Builder;
import com.chirp.community.model.LikesDto;

@Builder(toBuilder = true)
public record LikesReadResponse(
        long numLikes,
        LikeType likeType
) {
    public static LikesReadResponse of(LikesDto dto) {
        return LikesReadResponse
                .builder()
                .numLikes(dto.numLikes())
                .likeType(dto.likeType())
                .build();
    }
}
