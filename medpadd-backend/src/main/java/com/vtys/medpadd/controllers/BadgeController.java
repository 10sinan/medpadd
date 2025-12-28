package com.vtys.medpadd.controllers;

import com.vtys.medpadd.entities.Badge;
import com.vtys.medpadd.services.BadgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/badges")
@RequiredArgsConstructor
public class BadgeController {

    private final BadgeService service;

    @GetMapping
    public List<Badge> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Badge> getById(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Badge> create(@RequestBody Badge badge) {
        return ResponseEntity.ok(service.save(badge));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Badge> update(@PathVariable UUID id, @RequestBody Badge badge) {
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
