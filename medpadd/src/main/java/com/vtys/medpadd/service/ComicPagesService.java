package com.vtys.medpadd.service;

import com.vtys.medpadd.entity.ComicPages;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ComicPagesService {
    ComicPages save(ComicPages comicPage);

    Optional<ComicPages> findById(UUID id);

    List<ComicPages> findAll();

    void deleteById(UUID id);

    List<ComicPages> findByComicIdOrderByPageAsc(UUID comicId);
}
