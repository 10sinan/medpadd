package com.vtys.medpadd.dto;

import java.util.UUID;

public record ContentTagDto(
        UUID id,
        UUID iconId,
        String name) {
}
