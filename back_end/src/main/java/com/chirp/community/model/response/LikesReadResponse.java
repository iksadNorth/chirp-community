package com.chirp.community.model.response;

import com.chirp.community.type.LikeType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import com.chirp.community.model.LikesDto;

@Builder(toBuilder = true)
public record LikesReadResponse(
        @Schema(
                description = "추천수",
                example = "25"
        )
        long numLikes,
        @Schema(
                description = "추천 상태. '좋아요' and '싫어요' and 'Null'",
                example = "LIKE"
        )
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
