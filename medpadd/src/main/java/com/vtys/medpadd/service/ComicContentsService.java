package com.vtys.medpadd.service;

import com.vtys.medpadd.entity.ComicContents;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ComicContentsService {
    ComicContents save(ComicContents comicContent);

    Optional<ComicContents> findById(UUID id);

    List<ComicContents> findAll();

    void deleteById(UUID id);

    List<ComicContents> findByContentId(UUID contentId);
}
