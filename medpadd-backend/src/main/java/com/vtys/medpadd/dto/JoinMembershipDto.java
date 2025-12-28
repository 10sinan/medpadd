package com.vtys.medpadd.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record JoinMembershipDto(
        UUID id,
        UUID iconId,
        UUID contentCreatorId,
        String name,
        String description,
        BigDecimal price,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt) {
}
