package com.vtys.medpadd.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record RatingDto(
        UUID id,
        UUID contentId,
        UUID userId,
        BigDecimal rating,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt) {
}
