package com.vtys.medpadd.controllers;

import com.vtys.medpadd.entities.ContentCreatorVerificationBadge;
import com.vtys.medpadd.services.ContentCreatorVerificationBadgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/content-creator-verification-badges")
@RequiredArgsConstructor
public class ContentCreatorVerificationBadgeController {

    private final ContentCreatorVerificationBadgeService service;

    @GetMapping
    public List<ContentCreatorVerificationBadge> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentCreatorVerificationBadge> getById(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ContentCreatorVerificationBadge> create(@RequestBody ContentCreatorVerificationBadge badge) {
        return ResponseEntity.ok(service.save(badge));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContentCreatorVerificationBadge> update(@PathVariable UUID id,
            @RequestBody ContentCreatorVerificationBadge badge) {
        return service.findById(id)
                .map(existing -> {
                    badge.setId(existing.getId());
                    return ResponseEntity.ok(service.save(badge));
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
