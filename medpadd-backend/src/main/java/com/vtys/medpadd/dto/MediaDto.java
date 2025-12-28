package com.vtys.medpadd.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record MediaDto(
        UUID id,
        UUID userId,
        String mediaUrl,
        String mediaType,
        String mediaSize,
        OffsetDateTime createdAt) {
}
