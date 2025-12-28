package com.vtys.medpadd.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record AuditLogDto(
        UUID id,
        UUID userId,
        String action,
        String targetTable,
        UUID targetId,
        String oldValues,
        String newValues,
        OffsetDateTime createdAt) {
}
