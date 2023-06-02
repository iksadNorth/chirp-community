package com.chirp.community.model.response;

import com.chirp.community.model.SiteUserDto;
import com.chirp.community.type.RoleType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder(toBuilder = true)
public record SiteUserReadResponse(
        @Schema(
                description = "유저 ID",
                example = "432153"
        )
        Long id,
        @Schema(
                description = "이메일",
                example = "anyEamil@gmail.com"
        )
        String email,
        @Schema(
                description = "유저 닉네임",
                example = "신지드 전용 샴푸"
        )
        String nickname,
        @Schema(
                description = "유저 권한",
                example = "USER"
        )
        RoleType role,

        @Schema(
                description = "유저 정보 갱신 시, 새로 발급되는 JWT 토큰.",
                example = "anyheader.anypayload.anysignature"
        )
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
