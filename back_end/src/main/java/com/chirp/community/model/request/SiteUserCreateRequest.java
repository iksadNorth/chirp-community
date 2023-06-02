package com.chirp.community.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder(toBuilder = true)
public record SiteUserCreateRequest(
        @Schema(
                description = "이메일",
                example = "anyEamil@gmail.com"
        )
        String email,
        @Schema(
                description = "비밀 번호",
                example = "15363253"
        )
        String password,
        @Schema(
                description = "유저 닉네임",
                example = "신지드 전용 샴푸"
        )
        String nickname
) {}
