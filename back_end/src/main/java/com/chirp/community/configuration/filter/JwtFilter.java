package com.chirp.community.configuration.filter;

import com.chirp.community.exception.CommunityException;
import com.chirp.community.utils.JwtFilterUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.PublicKey;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;
    private final PublicKey publicKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // request의 Authorization 헤더 내용 검증 및 추출.
            String accessToken = JwtFilterUtils.parseToken(request);

            // 해당 토큰 검증
            log.trace("accessToken: {}", accessToken);
            Claims claims = JwtFilterUtils.validateJwtToken(accessToken, publicKey);
            String username = claims.getSubject();

            // 해당 토큰을 이용해 Authentication 객체 생성.
            UserDetails principal = JwtFilterUtils.loadPrincipalByUsername(userDetailsService, username);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    principal, null, principal.getAuthorities()
            );

            // Authentication 객체를 SecurityContextHolder에 보관.
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (CommunityException e) {
            // 오류 내용 logging.
            log.warn(e.getDescriptionForServer());

        } finally {
            filterChain.doFilter(request, response);

        }
    }
}
