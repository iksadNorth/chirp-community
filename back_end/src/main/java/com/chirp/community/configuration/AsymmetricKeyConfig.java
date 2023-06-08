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
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
@RequiredArgsConstructor
public class AsymmetricKeyConfig {
    private final JwtProperties jwtProperties;

    @Bean
    public PrivateKey privateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String trimmed = jwtProperties.getPrivateKeyTrimmed();
        KeySpec keySpec = new PKCS8EncodedKeySpec(getBytes(trimmed));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    @Bean
    public PublicKey publicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String trimmed = jwtProperties.getPublicKeyTrimmed();
        KeySpec keySpec = new X509EncodedKeySpec(getBytes(trimmed));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    private byte[] getBytes(String key) {
        return Base64.getDecoder().decode(key);
    }
}
