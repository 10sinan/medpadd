package com.vtys.medpadd.controllers;

import com.vtys.medpadd.entities.ContentTag;
import com.vtys.medpadd.services.ContentTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/content-tags")
@RequiredArgsConstructor
public class ContentTagController {

    private final ContentTagService service;

    @GetMapping
    public List<ContentTag> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentTag> getById(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ContentTag> create(@RequestBody ContentTag tag) {
        return ResponseEntity.ok(service.save(tag));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContentTag> update(@PathVariable UUID id, @RequestBody ContentTag tag) {
        return service.findById(id)
                .map(existing -> {
                    tag.setId(existing.getId());
                    return ResponseEntity.ok(service.save(tag));
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
