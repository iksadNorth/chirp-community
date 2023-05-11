package com.chirp.community.model.request;

import lombok.Builder;

@Builder(toBuilder = true)
public record BoardCreateRequest(
        String name
) {}
