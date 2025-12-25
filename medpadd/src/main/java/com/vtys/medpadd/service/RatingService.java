package com.vtys.medpadd.service;

import com.vtys.medpadd.entity.Rating;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RatingService {
    Rating save(Rating rating);

    Optional<Rating> findById(UUID id);

    List<Rating> findAll();

    void deleteById(UUID id);

    List<Rating> findByContentId(UUID contentId);

    List<Rating> findByUserId(UUID userId);

    Optional<Rating> findByUserIdAndContentId(UUID userId, UUID contentId);
}
