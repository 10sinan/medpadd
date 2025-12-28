package com.vtys.medpadd.dto;

import java.util.UUID;

public record ContentCreatorVerificationRelationDto(
        UUID verificationBadgeId,
        UUID contentCreatorId) {
}
