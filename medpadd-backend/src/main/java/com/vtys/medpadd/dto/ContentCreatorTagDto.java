package com.vtys.medpadd.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record ContentCreatorTagDto(
        UUID id,
        UUID iconId,
        String name,
        String description,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt) {
}
