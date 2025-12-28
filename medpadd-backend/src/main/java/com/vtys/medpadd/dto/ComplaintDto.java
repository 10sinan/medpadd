package com.vtys.medpadd.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record ComplaintDto(
        UUID id,
        UUID userId,
        String title,
        String complaint,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt) {
}
