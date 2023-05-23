package com.chirp.community.model.response;

import com.chirp.community.model.BoardDto;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
public record BoardReadResponse(
        Long id,
        LocalDateTime createdAt,
        String name
) {
    public static BoardReadResponse of(BoardDto dto) {
        return BoardReadResponse.builder()
                .id(dto.id())
                .createdAt(dto.createdAt())
                .name(dto.name())
                .build();
    }
}
