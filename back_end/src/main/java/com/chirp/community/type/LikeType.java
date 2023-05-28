package com.chirp.community.type;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor @Getter
public enum LikeType {
    LIKE(1),
    DISLIKE(-1),
    NULL(0);

    private final int value;

    @Converter(autoApply = true)
    public static class ConverterImpl implements AttributeConverter<LikeType, Integer> {

        @Override
        public Integer convertToDatabaseColumn(LikeType attribute) {
            return attribute.getValue();
        }

        @Override
        public LikeType convertToEntityAttribute(Integer dbData) {
            return Stream.of(LikeType.values())
                    .filter(r -> convertToDatabaseColumn(r).equals(dbData))
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }
    }
}
