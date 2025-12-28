package com.vtys.medpadd.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record CommentDto(
        UUID id,
        UUID contentId,
        UUID userId,
        UUID parentCommentId,
        String text,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt) {
}
