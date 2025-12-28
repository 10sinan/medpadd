package com.vtys.medpadd.service;

import com.vtys.medpadd.entity.ContentTagRelations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContentTagRelationsService {
    ContentTagRelations save(ContentTagRelations relation);

    Optional<ContentTagRelations> findById(UUID id);

    List<ContentTagRelations> findAll();

    void deleteById(UUID id);

    List<ContentTagRelations> findByContentId(UUID contentId);

    List<ContentTagRelations> findByTagId(UUID tagId);
}
