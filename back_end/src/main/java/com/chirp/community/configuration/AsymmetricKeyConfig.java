package com.chirp.community.configuration;

import com.chirp.community.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Configuration
@RequiredArgsConstructor
public class AsymmetricKeyConfig {
    private final JwtProperties jwtProperties;

    @Bean
    public PrivateKey privateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String trimmed = trim(jwtProperties.getPrivateKey());
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(getBytes(trimmed));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(privateKeySpec);
    }

    @Bean
    public PublicKey publicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String trimmed = trim(jwtProperties.getPublicKey());
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(getBytes(trimmed));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(publicKeySpec);
    }

    private String trim(String key) {
        Pattern pattern = Pattern.compile("-----([A-Z\\s]+)-----");
        Matcher matcher = pattern.matcher(key);
        return matcher.replaceAll("").replaceAll("\\s+", "");
    }

    private byte[] getBytes(String key) {
        return Base64.getDecoder().decode(key);
    }
}
