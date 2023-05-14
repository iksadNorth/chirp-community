package com.chirp.community.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("security.cors")
@NoArgsConstructor @Getter @Setter
public class CorsProperties {
        private String[] allowedOrigins;
}
