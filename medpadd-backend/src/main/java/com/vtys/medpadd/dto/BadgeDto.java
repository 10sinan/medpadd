package com.vtys.medpadd.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record BadgeDto(
        UUID id,
        UUID iconId,
        String code,
        String color,
        String name,
        String description,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt) {
}
