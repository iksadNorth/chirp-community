package com.chirp.community.service;

import com.chirp.community.entity.SiteUser;
import com.chirp.community.exception.CommunityException;
import com.chirp.community.model.SiteUserDto;
import com.chirp.community.repository.SiteUserRepository;
import com.chirp.community.type.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SiteUserServiceImpl implements SiteUserService {
    private final PasswordEncoder passwordEncoder;
    private final SiteUserRepository siteUserRepository;

    @Override
    public SiteUserDto create(String email, String password, String nickname) {
        SiteUser entity = SiteUser.of(email, passwordEncoder.encode(password), nickname);
        SiteUser saved = siteUserRepository.save(entity);
        return SiteUserDto.fromEntity(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public SiteUserDto readById(Long id) {
        return siteUserRepository.findById(id)
                .map(SiteUserDto::fromEntity)
                .orElseThrow(
                        () -> CommunityException.of(
                                HttpStatus.NOT_FOUND,
                                String.format("%s번 유저는 존재하지 않음.", id)
                        )
                );
    }

    @Override
    public SiteUserDto updateById(Long id, String email, String password, String nickname, RoleType role) {
        SiteUser entity = SiteUser.of(id, email, password, nickname);
        SiteUser saved = siteUserRepository.save(entity);
        return SiteUserDto.fromEntity(saved);
    }

    @Override
    public void deleteById(Long id) {
        siteUserRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return siteUserRepository.findByEmail(email)
                .map(SiteUserDto::fromEntity)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                String.format("이메일 '%s'을 가진 유저는 존재하지 않음.", email)
                        )
                );
    }
}
