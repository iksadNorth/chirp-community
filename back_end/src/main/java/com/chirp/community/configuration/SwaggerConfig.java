package com.chirp.community.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Chirp Community API")
                                .version("v1.0.0")
                                .summary("Chirp 프로젝트의 API 목록.")
                                .description("""
                                        API는 총 5개의 API 그룹으로 나뉘어져 있다.
                                        1. 게시판 API.
                                        2. 유저 API.
                                        3. 게시글 API.
                                        4. 댓글 API.
                                        5. 보안 관련 API.
                                        """)
                );
    }
}
