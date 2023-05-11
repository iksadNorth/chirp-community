package com.chirp.community.model;

import com.chirp.community.entity.Board;
import lombok.Builder;

@Builder(toBuilder = true)
public record BoardDto(
        Long id,
        String name
) {
    public static BoardDto fromEntity(Board entity) {
        return BoardDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
