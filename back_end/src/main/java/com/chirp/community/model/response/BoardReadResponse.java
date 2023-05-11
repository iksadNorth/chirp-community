package com.chirp.community.model.response;

import com.chirp.community.model.BoardDto;
import lombok.Builder;

@Builder(toBuilder = true)
public record BoardReadResponse(
        Long id,
        String name
) {
    public static BoardReadResponse of(BoardDto dto) {
        return BoardReadResponse.builder()
                .id(dto.id())
                .name(dto.name())
                .build();
    }
}
