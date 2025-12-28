package com.vtys.medpadd.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record ContentCreatorDto(
        UUID id,
        UUID userId,
        String biography,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt) {
}
