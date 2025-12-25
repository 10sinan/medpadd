package com.vtys.medpadd.service;

import com.vtys.medpadd.entity.Content;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContentService {
    Content save(Content content);

    Optional<Content> findById(UUID id);

    List<Content> findAll();

    void deleteById(UUID id);

    List<Content> findByContentCreatorId(UUID contentCreatorId);
}
