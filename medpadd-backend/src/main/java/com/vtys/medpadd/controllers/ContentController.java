package com.vtys.medpadd.controllers;

import com.vtys.medpadd.entities.Content;
import com.vtys.medpadd.services.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/contents")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService service;

    @GetMapping
    public List<Content> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Content> getById(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Content> create(@RequestBody Content content) {
        return ResponseEntity.ok(service.save(content));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Content> update(@PathVariable UUID id, @RequestBody Content content) {
        return service.findById(id)
                .map(existing -> {
                    content.setId(existing.getId());
                    return ResponseEntity.ok(service.save(content));
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
