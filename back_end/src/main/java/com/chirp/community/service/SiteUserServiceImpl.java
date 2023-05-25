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

import static com.chirp.community.utils.AccessAuthorizer.isAdmin;
import static com.chirp.community.utils.AccessAuthorizer.ownershipCheck;
import static com.chirp.community.utils.CheckTools.nullCheck;

@Service
@Transactional
@RequiredArgsConstructor
public class SiteUserServiceImpl implements SiteUserService {
    private final PasswordEncoder passwordEncoder;
    private final SiteUserRepository siteUserRepository;

    public SiteUser loadSiteUserById(Long id) {
        return siteUserRepository.findById(id)
                .orElseThrow(
                        () -> CommunityException.of(
                                HttpStatus.NOT_FOUND,
                                String.format("%s번 유저는 존재하지 않음.", id)
                        )
                );
    }

    private void overwriteWithBlankCheck(SiteUser entity, String email, String password, String nickname, RoleType role) {
        if(email!=null)
            entity.setEmail(email);
        if(password!=null)
            entity.setPassword(passwordEncoder.encode(password));
        if(nickname!=null)
            entity.setNickname(nickname);
        if(role!=null)
            entity.setRole(role);
    }

    @Override
    public SiteUserDto create(String email, String password, String nickname) {
        nullCheck(email, password, nickname);
        SiteUser entity = SiteUser.of(email, passwordEncoder.encode(password), nickname);
        SiteUser saved = siteUserRepository.save(entity);
        return SiteUserDto.fromEntity(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public SiteUserDto readById(Long id) {
        return SiteUserDto.fromEntity(loadSiteUserById(id));
    }

    @Override
    public SiteUserDto updateByAuthToken(SiteUserDto principal, String email, String password, String nickname, RoleType role) {
        SiteUser entity = principal.toEntity();

        overwriteWithBlankCheck(entity, email, password, nickname, role);

        SiteUser saved = siteUserRepository.save(entity);
        return SiteUserDto.fromEntity(saved);
    }

    @Override
    public SiteUserDto updateById(Long id, String email, String password, String nickname, RoleType role) {
        if(!isAdmin() && !ownershipCheck(id)) {
            throw CommunityException.of(
                    HttpStatus.FORBIDDEN,
                    "해당 사용자로의 접근은 금지되어 있습니다.",
                    String.format("id가 '%s'인 사용자가 다른 사용자의 정보를 수정하려 했습니다. 혹은 로그인하지 않은 유저의 정보 수정일 수도 있습니다.", id)
            );
        }

        SiteUser entity = loadSiteUserById(id);

        overwriteWithBlankCheck(entity, email, password, nickname, role);

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
