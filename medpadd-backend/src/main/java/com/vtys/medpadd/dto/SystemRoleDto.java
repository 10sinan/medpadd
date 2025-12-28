package com.vtys.medpadd.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record SystemRoleDto(
        UUID id,
        String name,
        String description,
        OffsetDateTime createdAt) {
}
