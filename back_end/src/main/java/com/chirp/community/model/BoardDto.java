package com.chirp.community.model;

import com.chirp.community.entity.Board;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
public record BoardDto(
        Long id,
        LocalDateTime createdAt,
        String name
) {
    public static BoardDto fromEntity(Board entity) {
        return BoardDto.builder()
                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .name(entity.getName())
                .build();
    }
}
