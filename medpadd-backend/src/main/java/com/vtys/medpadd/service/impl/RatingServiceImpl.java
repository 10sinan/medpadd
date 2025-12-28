package com.vtys.medpadd.service.impl;

import com.vtys.medpadd.entity.Rating;
import com.vtys.medpadd.repository.RatingRepository;
import com.vtys.medpadd.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    @Override
    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Optional<Rating> findById(UUID id) {
        return ratingRepository.findById(id);
    }

    @Override
    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        ratingRepository.deleteById(id);
    }

    @Override
    public List<Rating> findByContentId(UUID contentId) {
        return ratingRepository.findByContentId(contentId);
    }

    @Override
    public List<Rating> findByUserId(UUID userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public Optional<Rating> findByUserIdAndContentId(UUID userId, UUID contentId) {
        return ratingRepository.findByUserIdAndContentId(userId, contentId);
    }
}
