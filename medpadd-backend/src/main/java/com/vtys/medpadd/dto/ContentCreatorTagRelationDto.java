package com.vtys.medpadd.dto;

import java.util.UUID;

public record ContentCreatorTagRelationDto(
        UUID tagId,
        UUID contentCreatorId) {
}
