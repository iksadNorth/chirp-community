package com.chirp.community.model.request;

import com.chirp.community.type.RoleType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder(toBuilder = true)
public record SiteUserUpdateRequest(
        @Schema(
                description = "이메일",
                example = "anyEamil@gmail.com"
        )
        String email,
        @Schema(
                description = "유저 비밀번호",
                example = "q1w2e3r4"
        )
        String password,
        @Schema(
                description = "유저 닉네임",
                example = "신지드 전용 샴푸"
        )
        String nickname
) {}
