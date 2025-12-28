package com.vtys.medpadd.dto;

import java.util.UUID;

public record ContentTagRelationDto(
        UUID tagId,
        UUID contentId) {
}
