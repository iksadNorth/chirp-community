package com.chirp.community.service;

import com.chirp.community.entity.SiteUser;
import com.chirp.community.type.RoleType;

public interface AuthService {
    String getJwtToken(String email, String password);

    String getJwtToken(SiteUser entity);

    void sendCodeWithEmail(Long id, String email, RoleType roleType);

    String verifyCodeWithEmail(Long userId, String code);
}
