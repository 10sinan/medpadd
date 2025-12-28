package com.vtys.medpadd.dto;

import java.util.UUID;

public record ContentJoinMembershipRelationDto(
        UUID joinMembershipId,
        UUID contentId) {
}
