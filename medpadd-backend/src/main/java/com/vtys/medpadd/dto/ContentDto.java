package com.vtys.medpadd.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record ContentDto(
        UUID id,
        UUID contentCreatorId,
        String title,
        BigDecimal price,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt) {
}
