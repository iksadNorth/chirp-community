package com.chirp.community.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

public class JwtTokenWithRS256Utils {
    public static String generateJwtToken(String username, Claims claims, Long expiredTimeMs, PublicKey publicKey) {
        long now = System.currentTimeMillis();

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + expiredTimeMs))
                .setClaims(claims)
                .signWith(publicKey, SignatureAlgorithm.RS256)
                .compact();
    }

    public static String generateJwtToken(String username, Long expiredTimeMs, PublicKey publicKey) {
        return generateJwtToken(username, null, expiredTimeMs, publicKey);
    }

    public static Claims decodeJwtToken(String token, PrivateKey privateKey) {
        return Jwts.parserBuilder()
                .setSigningKey(privateKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
