package com.chirp.community.model;

import com.chirp.community.entity.SiteUser;
import com.chirp.community.type.RoleType;
import lombok.Builder;

@Builder(toBuilder = true)
public record SiteUserDto(
        Long id,
        String email,
        String password,
        String nickname,
        RoleType role
) {
    public static SiteUserDto fromEntity(SiteUser entity) {
        return SiteUserDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .nickname(entity.getNickname())
                .role(entity.getRole())
                .build();
    }
}
