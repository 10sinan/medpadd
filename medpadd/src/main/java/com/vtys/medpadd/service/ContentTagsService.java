package com.vtys.medpadd.service;

import com.vtys.medpadd.entity.ContentTags;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContentTagsService {
    ContentTags save(ContentTags tag);

    Optional<ContentTags> findById(UUID id);

    List<ContentTags> findAll();

    void deleteById(UUID id);
}
