package com.chirp.community.repository;

import com.chirp.community.entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SiteUserRepository extends JpaRepository<SiteUser, Long> {
    Optional<SiteUser> findByEmail(String email);
}
