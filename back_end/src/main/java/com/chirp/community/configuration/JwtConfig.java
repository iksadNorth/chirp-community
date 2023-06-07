package com.chirp.community.configuration;

import com.chirp.community.entity.SiteUser;
import com.chirp.community.utils.ConvertToJwtClaims;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    @Bean
    public ConvertToJwtClaims<SiteUser> converterToJwtClaims() {
        return entity -> {
            Claims claims = Jwts.claims();
            claims.setSubject(entity.getEmail());
            claims.put(ClaimsKey.ID.getKeyNameInJwt(), entity.getId());
            claims.put(ClaimsKey.NICKNAME.getKeyNameInJwt(), entity.getNickname());
            claims.put(ClaimsKey.ROLE.getKeyNameInJwt(), entity.getRole().getJwtName());
            return claims;
        };
    }

    @RequiredArgsConstructor @Getter
    public enum ClaimsKey {
        ID("ID"),
        NICKNAME("NICKNAME"),
        ROLE("ROLE");

        private final String keyNameInJwt;
    }
}
