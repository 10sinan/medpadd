package com.vtys.medpadd.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record PaintingContentDto(
        UUID id,
        UUID contentId,
        UUID imageId,
        String style,
        String description,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt) {
}
