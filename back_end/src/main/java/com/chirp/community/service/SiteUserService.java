package com.chirp.community.service;

import com.chirp.community.model.SiteUserDto;
import com.chirp.community.type.RoleType;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface SiteUserService extends UserDetailsService {
    SiteUserDto create(String email, String password, String nickname);

    SiteUserDto readById(Long id);

    SiteUserDto updateById(Long id, String email, String password, String nickname, RoleType role);

    void deleteById(Long id);
}
