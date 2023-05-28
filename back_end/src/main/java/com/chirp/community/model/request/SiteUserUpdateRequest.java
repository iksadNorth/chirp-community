package com.chirp.community.model.request;

import com.chirp.community.type.RoleType;
import lombok.Builder;

@Builder(toBuilder = true)
public record SiteUserUpdateRequest(
        String email,
        String password,
        String nickname
) {}
