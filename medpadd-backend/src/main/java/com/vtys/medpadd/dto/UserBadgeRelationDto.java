package com.vtys.medpadd.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record UserBadgeRelationDto(
        UUID userId,
        UUID badgeId,
        OffsetDateTime createdAt) {
}
