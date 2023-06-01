package com.chirp.community.model.projection;

import java.time.LocalDateTime;

public interface BaseMapper {
    Long getId();
    LocalDateTime getCreatedAt();
}
