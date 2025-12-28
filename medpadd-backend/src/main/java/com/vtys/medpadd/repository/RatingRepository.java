package com.vtys.medpadd.repository;

import com.vtys.medpadd.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RatingRepository extends JpaRepository<Rating, UUID> {
    List<Rating> findByContentId(UUID contentId);

    List<Rating> findByUserId(UUID userId);

    Optional<Rating> findByUserIdAndContentId(UUID userId, UUID contentId);
}
