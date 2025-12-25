package com.vtys.medpadd.service;

import com.vtys.medpadd.entity.PoetryContents;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PoetryContentsService {
    PoetryContents save(PoetryContents poetryContent);

    Optional<PoetryContents> findById(UUID id);

    List<PoetryContents> findAll();

    void deleteById(UUID id);

    List<PoetryContents> findByContentId(UUID contentId);
}
