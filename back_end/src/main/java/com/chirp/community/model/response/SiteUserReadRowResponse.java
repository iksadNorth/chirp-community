package com.chirp.community.model.response;

import com.chirp.community.model.SiteUserDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder(toBuilder = true)
public record SiteUserReadRowResponse(
        @Schema(
                description = "유저 ID",
                example = "1534356"
        )
        Long id,
        @Schema(
                description = "유저 닉네임",
                example = "신지드 전용 삼푸"
        )
        String nickname
) {
    public static SiteUserReadRowResponse of(SiteUserDto dto) {
        return SiteUserReadRowResponse.builder()
                .id(dto.id())
                .nickname(dto.nickname())
                .build();
    }
}
