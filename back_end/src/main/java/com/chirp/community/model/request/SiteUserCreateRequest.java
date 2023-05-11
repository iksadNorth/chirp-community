package com.chirp.community.model.request;

import lombok.Builder;

@Builder(toBuilder = true)
public record SiteUserCreateRequest(
        String email,
        String password,
        String nickname
) {}
