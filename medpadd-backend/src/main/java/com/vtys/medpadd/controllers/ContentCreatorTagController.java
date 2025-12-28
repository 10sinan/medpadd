package com.vtys.medpadd.controllers;

import com.vtys.medpadd.entities.ContentCreatorTag;
import com.vtys.medpadd.services.ContentCreatorTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/content-creator-tags")
@RequiredArgsConstructor
public class ContentCreatorTagController {

    private final ContentCreatorTagService service;

    @GetMapping
    public List<ContentCreatorTag> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentCreatorTag> getById(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ContentCreatorTag> create(@RequestBody ContentCreatorTag tag) {
        return ResponseEntity.ok(service.save(tag));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContentCreatorTag> update(@PathVariable UUID id, @RequestBody ContentCreatorTag tag) {
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
