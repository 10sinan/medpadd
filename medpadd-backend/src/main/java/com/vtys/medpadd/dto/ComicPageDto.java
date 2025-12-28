package com.vtys.medpadd.dto;

import java.util.UUID;

public record ComicPageDto(
        UUID id,
        UUID comicId,
        String pageUrl,
        Integer pageNumber) {
}
