package com.chirp.community.configuration;

import com.chirp.community.properties.CorsProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
public class CorsConfig {
    private final CorsProperties corsProperties;

    @Bean
    public CorsConfiguration corsConfiguration() {
        CorsConfiguration config = new CorsConfiguration();
        for(String origin : corsProperties.getAllowedOrigins()) {
            config.addAllowedOrigin(origin);
        }
        config.addAllowedMethod(CorsConfiguration.ALL);
        config.addAllowedHeader("Authorization");
        return config;
    }

    @Bean
    public CorsConfigurationSource configurationSource(@Autowired CorsConfiguration corsConfiguration) {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", corsConfiguration);
        return source;
    }
}
