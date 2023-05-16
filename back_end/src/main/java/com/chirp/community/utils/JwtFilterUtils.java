package com.chirp.community.utils;

import com.chirp.community.exception.CommunityException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.security.PublicKey;

public class JwtFilterUtils {
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    public static String parseToken(HttpServletRequest request) throws CommunityException {
        String token = request.getHeader(AUTHORIZATION);

        if(token == null) {
            throw CommunityException.of(HttpStatus.UNAUTHORIZED, "토큰 헤더가 존재하지 않습니다.");

        } else if (token.isBlank()) {
            throw CommunityException.of(HttpStatus.UNAUTHORIZED, "토큰 헤더가 존재하긴 하지만 빈 문자열입니다.");

        } else if (!token.startsWith(BEARER_PREFIX)) {
            String message = String.format("토큰 헤더가 존재하긴 하지만 %s로 시작하지 않습니다.", BEARER_PREFIX);
            throw CommunityException.of(HttpStatus.UNAUTHORIZED, message);

        }

        return token.substring(BEARER_PREFIX.length());
    }

    public static Claims validateJwtToken(String accessToken, PublicKey publicKey) throws CommunityException {
        try {
            return JwtTokenWithRS256Utils.decodeJwtToken(accessToken, publicKey);

        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            throw CommunityException.of(HttpStatus.UNAUTHORIZED, "잘못된 JWT 서명입니다.");

        } catch (ExpiredJwtException e) {
            throw CommunityException.of(HttpStatus.UNAUTHORIZED, "기간 만료된 JWT 토큰입니다.");

        } catch (UnsupportedJwtException e) {
            throw CommunityException.of(HttpStatus.UNAUTHORIZED, "지원되지 않는 JWT 토큰입니다.");


        } catch (IllegalArgumentException e) {
            throw CommunityException.of(HttpStatus.UNAUTHORIZED, "JWT 토큰이 잘못되었습니다.");

        }
    }

    public static UserDetails loadPrincipalByUsername(UserDetailsService userDetailsService, String username) throws CommunityException {
        try {
            return userDetailsService.loadUserByUsername(username);

        } catch (UsernameNotFoundException e) {
            throw CommunityException.of(
                    HttpStatus.NOT_FOUND,
                    String.format("%s \nJWT 토큰의 Subject, 즉, 회원 ID가 위조되었거나 삭제되었을 수 있습니다.", e.getMessage()),
                    e.getMessage()
            );
        }
    }
}
