package com.vtys.medpadd.service;

import com.vtys.medpadd.entity.ContentCreators;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContentCreatorsService {
    ContentCreators save(ContentCreators contentCreator);

    Optional<ContentCreators> findById(UUID id);

    List<ContentCreators> findAll();

    void deleteById(UUID id);

    Optional<ContentCreators> findByUserId(UUID userId);
}
