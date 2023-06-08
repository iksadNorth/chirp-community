package com.chirp.community.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

public class JwtTokenWithRS256Utils {
    public static String generateJwtToken(Claims claims, long expiredTimeMs, PrivateKey privateKey) {
        long now = System.currentTimeMillis();
        claims.setIssuedAt(new Date(now));
        claims.setExpiration(new Date(now + expiredTimeMs));
        return Jwts.builder()
                .setClaims(claims)
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    public static Claims decodeJwtToken(String token, PublicKey publicKey) {
        return Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
