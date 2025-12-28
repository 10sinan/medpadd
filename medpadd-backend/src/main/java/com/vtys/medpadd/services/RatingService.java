package com.vtys.medpadd.services;

import com.vtys.medpadd.entities.Rating;
import com.vtys.medpadd.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RatingService {

    private final RatingRepository repository;

    public List<Rating> findAll() {
        return repository.findAll();
    }

    public Optional<Rating> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    public Rating save(Rating rating) {
        return repository.save(rating);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
