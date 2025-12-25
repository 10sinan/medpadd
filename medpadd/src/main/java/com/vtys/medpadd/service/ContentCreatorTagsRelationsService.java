package com.vtys.medpadd.service;

import com.vtys.medpadd.entity.ContentCreatorTagsRelations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContentCreatorTagsRelationsService {
    ContentCreatorTagsRelations save(ContentCreatorTagsRelations relation);

    Optional<ContentCreatorTagsRelations> findById(UUID id);

    List<ContentCreatorTagsRelations> findAll();

    void deleteById(UUID id);

    List<ContentCreatorTagsRelations> findByContentCreatorId(UUID contentCreatorId);

    List<ContentCreatorTagsRelations> findByTagId(UUID tagId);
}
