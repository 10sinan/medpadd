package com.vtys.medpadd.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record FollowRelationDto(
        UUID userId,
        UUID contentCreatorId,
        OffsetDateTime createdAt) {
}
