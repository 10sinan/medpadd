package com.vtys.medpadd.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record SubscriptionDto(
        UUID id,
        UUID iconId,
        String name,
        String description,
        String privileges,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt) {
}
