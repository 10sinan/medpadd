package com.vtys.medpadd.service;

import com.vtys.medpadd.entity.Media;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MediaService {
    Media save(Media media);

    Optional<Media> findById(UUID id);

    List<Media> findAll();

    void deleteById(UUID id);

    List<Media> findByUserId(UUID userId);
}
