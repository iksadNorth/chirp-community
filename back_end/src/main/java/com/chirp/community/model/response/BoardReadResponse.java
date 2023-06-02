package com.chirp.community.model.response;

import com.chirp.community.model.BoardDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
public record BoardReadResponse(
        @Schema(
                description = "게시판 ID",
                example = "15363253"
        )
        Long id,
        @Schema(
                description = "게시판 생성일",
                example = "1970-01-01T00:00:00.000+00:00"
        )
        LocalDateTime createdAt,
        @Schema(
                description = "게시판 이름",
                example = "League Of Legend"
        )
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
