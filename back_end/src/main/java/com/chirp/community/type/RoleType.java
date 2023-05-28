package com.chirp.community.type;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.util.stream.Stream;

@RequiredArgsConstructor @Getter
public enum RoleType {
    USER("ROLE_USER", "USER", "일반 회원"),
    USER_VERIFIED_WITH_EMAIL("ROLE_USER_VERIFIED_WITH_EMAIL", "USER_VERIFIED_WITH_EMAIL", "이메일 인증된 일반 회원"),
    PRIME_ADMIN("ROLE_PRIME_ADMIN", "PRIME_ADMIN", "전체 관리자"),
    BOARD_ADMIN("ROLE_BOARD_ADMIN", "BOARD_ADMIN", "게시판 관리자");

    private final String dbName;
    private final String jwtName;
    private final String description;

    @Converter(autoApply = true)
    public static class ConverterImpl implements AttributeConverter<RoleType, String> {

        @Override
        public String convertToDatabaseColumn(RoleType attribute) {
            return attribute.getDbName();
        }

        @Override
        public RoleType convertToEntityAttribute(String dbData) {
            return Stream.of(RoleType.values())
                    .filter(r -> convertToDatabaseColumn(r).equals(dbData))
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }
    }
}
