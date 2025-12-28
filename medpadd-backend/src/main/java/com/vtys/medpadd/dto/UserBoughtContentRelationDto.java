package com.vtys.medpadd.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record UserBoughtContentRelationDto(
        UUID userId,
        UUID contentId,
        OffsetDateTime createdAt) {
}
