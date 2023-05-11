package com.chirp.community.service;

import com.chirp.community.entity.SiteUser;
import com.chirp.community.exception.CommunityException;
import com.chirp.community.model.SiteUserDto;
import com.chirp.community.repository.SiteUserRepository;
import com.chirp.community.type.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SiteUserServiceImpl implements SiteUserService {
    private final SiteUserRepository siteUserRepository;

    @Override
    public SiteUserDto create(String email, String password, String nickname) {
        SiteUser entity = SiteUser.of(email, password, nickname);
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
}
