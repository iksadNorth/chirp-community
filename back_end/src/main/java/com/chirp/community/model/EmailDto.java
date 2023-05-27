package com.chirp.community.model;

import lombok.Builder;

@Builder(toBuilder = true)
public record EmailDto(
        String title,
        String content
) {
}
