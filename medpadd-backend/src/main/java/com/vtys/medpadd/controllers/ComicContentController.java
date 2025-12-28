package com.vtys.medpadd.controllers;

import com.vtys.medpadd.entities.ComicContent;
import com.vtys.medpadd.services.ComicContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/comic-contents")
@RequiredArgsConstructor
public class ComicContentController {

    private final ComicContentService service;

    @GetMapping
    public List<ComicContent> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComicContent> getById(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ComicContent> create(@RequestBody ComicContent comicContent) {
        return ResponseEntity.ok(service.save(comicContent));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComicContent> update(@PathVariable UUID id, @RequestBody ComicContent comicContent) {
        return service.findById(id)
                .map(existing -> {
                    comicContent.setId(existing.getId());
                    return ResponseEntity.ok(service.save(comicContent));
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
