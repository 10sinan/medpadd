package com.vtys.medpadd.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record UserJoinMembershipRelationDto(
        UUID userId,
        UUID joinMembershipId,
        OffsetDateTime createdAt) {
}
