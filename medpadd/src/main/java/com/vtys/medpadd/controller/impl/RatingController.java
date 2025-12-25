package com.vtys.medpadd.controller.impl;

import com.vtys.medpadd.entity.Rating;
import com.vtys.medpadd.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @PostMapping
    public Rating create(@RequestBody Rating rating) {
        return ratingService.save(rating);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rating> getById(@PathVariable UUID id) {
        return ratingService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Rating> getAll() {
        return ratingService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        ratingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-content/{contentId}")
    public List<Rating> getByContent(@PathVariable UUID contentId) {
        return ratingService.findByContentId(contentId);
    }

    @GetMapping("/by-user/{userId}")
    public List<Rating> getByUser(@PathVariable UUID userId) {
        return ratingService.findByUserId(userId);
    }

    @GetMapping("/by-user/{userId}/content/{contentId}")
    public ResponseEntity<Rating> getByUserAndContent(@PathVariable UUID userId, @PathVariable UUID contentId) {
        return ratingService.findByUserIdAndContentId(userId, contentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
