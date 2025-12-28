package com.vtys.medpadd.dto;

import java.util.UUID;

public record ContentCreatorRoleRelationDto(
        UUID contentCreatorRoleId,
        UUID contentCreatorId) {
}
