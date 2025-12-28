package com.vtys.medpadd.dto;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

public record UserDto(
        UUID id,
        UUID profilePicId,
        UUID roleId,
        String firstName,
        String lastName,
        String username,
        LocalDate birthday,
        String email,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt) {
}
