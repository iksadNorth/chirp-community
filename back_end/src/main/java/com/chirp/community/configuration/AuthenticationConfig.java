package com.chirp.community.configuration;

import com.chirp.community.configuration.filter.JwtFilter;
import com.chirp.community.exception.CommunityAccessDeniedHandler;
import com.chirp.community.exception.CommunityAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestHandler;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfigurationSource;

import java.security.PublicKey;

@Configuration
@RequiredArgsConstructor
public class AuthenticationConfig {
    private static final String prefix = "/api/v1";
    private final CorsConfigurationSource configurationSource;
    private final UserDetailsService userDetailsService;
    private final PublicKey publicKey;

    private final CommunityAccessDeniedHandler communityAccessDeniedHandler;
    private final CommunityAuthenticationEntryPoint communityAuthenticationEntryPoint;

    private final CsrfTokenRepository csrfTokenRepository;
    private final CsrfTokenRequestHandler csrfTokenRequestHandler;
    private final RequestMatcher[] requestMatchers;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return authorizeHttpRequests(http)
                // JWT 토큰을 사용할 것이기 때문에 각 요청마다 ID와 Password를 제출할 이유가 없다.
                .httpBasic().disable()
                // header 보안 관련 설정 중 h2-console을 사용할 수 있게
                // X-Frame-Options 헤더의 값을 sameorigin을 설정.
                .headers()
                    .addHeaderWriter(new XFrameOptionsHeaderWriter(
                            XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN
                    )).and()
                // API 서버를 설계하는 것이므로 HttpSessionCsrfTokenRepository를 이용해 CSRF 토큰을
                // 저장할 수 없다. 때문에 CookieCsrfTokenRepository를 사용해서 토큰을 저장한다.
                // H2DB Console을 사용하고 위해 '/h2/**' 는 제외시킴.
                .csrf()
//                    .ignoringRequestMatchers(requestMatchers)
//                    .csrfTokenRequestHandler(csrfTokenRequestHandler)
//                    .csrfTokenRepository(csrfTokenRepository).and()
                .disable()
                // API 서버를 만드는 것이므로 formLogin과 logout은 필요없다.
                .formLogin().disable()
                .logout().disable()
                // API 서버를 만드는 것이므로 STATELESS하게 세션을 설정한다.
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // 인증 혹은 인가 오류 시, 대처 방법에 대해 서술하고 있다.
                .exceptionHandling()
                    .authenticationEntryPoint(communityAuthenticationEntryPoint)
                    .accessDeniedHandler(communityAccessDeniedHandler).and()
                // 익명 인증 처리 필터 비활성화.
                .anonymous().disable()
                // Remember Me 인증 처리 필터 비활성화.
                .rememberMe().disable()
                // CORS에 대한 설정.
                .cors().configurationSource(configurationSource).and()
                // JwtFilter 배치.
                .addFilterBefore(new JwtFilter(userDetailsService, publicKey), UsernamePasswordAuthenticationFilter.class)

                .build();
    }

    public HttpSecurity authorizeHttpRequests(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(
                reg -> reg
                        .requestMatchers(HttpMethod.GET, "/api/v1/user/me/**").authenticated()

                        .requestMatchers(HttpMethod.PATCH, "/api/v1/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/**").authenticated()
                        .anyRequest().permitAll()
                );
    }
}
