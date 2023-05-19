package com.chirp.community.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

public class JwtTokenWithRS256Utils {
    public static String generateJwtToken(String username, Claims claims, Long expiredTimeMs, PrivateKey privateKey) {
        long now = System.currentTimeMillis();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + expiredTimeMs))
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    public static String generateJwtToken(String username, Long expiredTimeMs, PrivateKey privateKey) {
        return generateJwtToken(username, null, expiredTimeMs, privateKey);
    }

    public static Claims decodeJwtToken(String token, PublicKey publicKey) {
        return Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
