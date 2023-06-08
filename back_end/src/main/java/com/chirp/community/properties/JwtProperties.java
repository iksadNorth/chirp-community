package com.chirp.community.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Configuration
@ConfigurationProperties("security.jwt")
@NoArgsConstructor @Getter @Setter
public class JwtProperties {
        private String publicKey;
        private String privateKey;
        private Long expiredTimeMs;

        private static String getKeyTrimmed(String key) {
                Pattern pattern = Pattern.compile("-----([A-Z\\s]+)-----");
                Matcher matcher = pattern.matcher(key);
                return matcher.replaceAll("").replaceAll("\\s+", "");
        }

        public String getPublicKeyTrimmed() {
                return getKeyTrimmed(publicKey);
        }

        public String getPrivateKeyTrimmed() {
                return getKeyTrimmed(privateKey);
        }
}
