package com.chirp.community.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("spring.redis")
@NoArgsConstructor @Getter @Setter
public class RedisProperties {
        private String host;
        private int port;
}
