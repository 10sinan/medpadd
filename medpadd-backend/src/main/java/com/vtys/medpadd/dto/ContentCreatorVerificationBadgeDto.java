package com.vtys.medpadd.dto;

import java.util.UUID;

public record ContentCreatorVerificationBadgeDto(
        UUID id,
        UUID iconId,
        String code,
        String name,
        String color) {
}
