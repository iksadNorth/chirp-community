package com.chirp.community.model;

import com.chirp.community.entity.SiteUser;
import com.chirp.community.type.RoleType;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Builder(toBuilder = true)
public record SiteUserDto(
        Long id,
        String email,
        String password,
        String nickname,
        RoleType role
) implements UserDetails {
    public static SiteUserDto fromEntity(SiteUser entity) {
        return SiteUserDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .nickname(entity.getNickname())
                .role(entity.getRole())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.role.getDbName());
        return List.of(authority);
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
