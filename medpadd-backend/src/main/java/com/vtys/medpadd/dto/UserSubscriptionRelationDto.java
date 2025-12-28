package com.vtys.medpadd.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record UserSubscriptionRelationDto(
        UUID userId,
        UUID subscriptionId,
        OffsetDateTime createdAt) {
}
