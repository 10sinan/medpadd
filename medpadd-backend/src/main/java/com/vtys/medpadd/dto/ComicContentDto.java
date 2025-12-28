package com.vtys.medpadd.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record ComicContentDto(
        UUID id,
        UUID contentId,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt) {
}
