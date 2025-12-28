package com.vtys.medpadd.controllers;

import com.vtys.medpadd.entities.Rating;
import com.vtys.medpadd.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService service;

    @GetMapping
    public List<Rating> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rating> getById(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating) {
        return ResponseEntity.ok(service.save(rating));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rating> update(@PathVariable UUID id, @RequestBody Rating rating) {
        return service.findById(id)
                .map(existing -> {
                    rating.setId(existing.getId());
                    return ResponseEntity.ok(service.save(rating));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (service.findById(id).isEmpty())
            return ResponseEntity.notFound().build();
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
