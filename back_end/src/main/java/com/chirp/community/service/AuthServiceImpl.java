package com.chirp.community.service;

import com.chirp.community.entity.SiteUser;
import com.chirp.community.exception.CommunityException;
import com.chirp.community.properties.JwtProperties;
import com.chirp.community.repository.SiteUserRepository;
import com.chirp.community.utils.JwtTokenWithRS256Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.PrivateKey;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final SiteUserRepository siteUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProperties jwtProperties;
    private final PrivateKey privateKey;

    @Transactional(readOnly = true)
    @Override
    public String getJwtToken(String email, String password) {
        SiteUser entity = siteUserRepository.findByEmail(email)
                .orElseThrow(
                        () -> CommunityException.of(HttpStatus.UNAUTHORIZED, String.format("'%s'는 존재하지 않는 계정입니다.", email))
                );

        if(!passwordEncoder.matches(password, entity.getPassword())) {
            throw CommunityException.of(HttpStatus.UNAUTHORIZED, "올바른 비밀번호가 아닙니다.");
        }

        return JwtTokenWithRS256Utils.generateJwtToken(email, jwtProperties.getExpiredTimeMs(), privateKey);
    }
}
