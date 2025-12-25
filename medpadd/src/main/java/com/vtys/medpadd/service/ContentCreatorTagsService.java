package com.vtys.medpadd.service;

import com.vtys.medpadd.entity.ContentCreatorTags;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContentCreatorTagsService {
    ContentCreatorTags save(ContentCreatorTags tag);

    Optional<ContentCreatorTags> findById(UUID id);

    List<ContentCreatorTags> findAll();

    void deleteById(UUID id);

    Optional<ContentCreatorTags> findByName(String name);
}
