package com.vtys.medpadd.service;

import com.vtys.medpadd.entity.PaintingContents;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaintingContentsService {
    PaintingContents save(PaintingContents paintingContent);

    Optional<PaintingContents> findById(UUID id);

    List<PaintingContents> findAll();

    void deleteById(UUID id);

    List<PaintingContents> findByContentId(UUID contentId);
}
