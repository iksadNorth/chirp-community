package com.chirp.community.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("security.jwt")
@NoArgsConstructor @Getter @Setter
public class JwtProperties {
        private String publicKey;
        private String privateKey;
        private Long expiredTimeMs;
}
