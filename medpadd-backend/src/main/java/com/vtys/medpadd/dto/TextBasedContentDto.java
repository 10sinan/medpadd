package com.vtys.medpadd.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record TextBasedContentDto(
        UUID id,
        UUID contentId,
        String text,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt) {
}
