package com.chirp.community.model.response;

import com.chirp.community.model.SiteUserDto;
import lombok.Builder;

@Builder(toBuilder = true)
public record SiteUserReadRowResponse(
        Long id,
        String nickname
) {
    public static SiteUserReadRowResponse of(SiteUserDto dto) {
        return SiteUserReadRowResponse.builder()
                .id(dto.id())
                .nickname(dto.nickname())
                .build();
    }
}
