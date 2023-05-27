package com.chirp.community.model.response;

import com.chirp.community.model.SiteUserDto;
import com.chirp.community.type.RoleType;
import lombok.Builder;

@Builder(toBuilder = true)
public record SiteUserReadResponse(
        Long id,
        String email,
        String nickname,
        RoleType role,

        String token
) {
    public static SiteUserReadResponse of(SiteUserDto dto) {
        return SiteUserReadResponse.builder()
                .id(dto.id())
                .email(dto.email())
                .nickname(dto.nickname())
                .role(dto.role())

                .token(dto.token())
                .build();
    }
}
