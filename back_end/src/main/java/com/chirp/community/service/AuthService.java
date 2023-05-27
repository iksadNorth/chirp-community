package com.chirp.community.service;

import com.chirp.community.type.RoleType;

public interface AuthService {
    String getJwtToken(String email, String password);

    void sendCodeWithEmail(Long id, String email, RoleType roleType);

    void verifyCodeWithEmail(Long userId, String code);
}
