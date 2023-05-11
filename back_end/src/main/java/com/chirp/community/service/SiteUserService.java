package com.chirp.community.service;

import com.chirp.community.model.SiteUserDto;
import com.chirp.community.type.RoleType;

public interface SiteUserService {
    SiteUserDto create(String email, String password, String nickname);

    SiteUserDto readById(Long id);

    SiteUserDto updateById(Long id, String email, String password, String nickname, RoleType role);

    void deleteById(Long id);
}
