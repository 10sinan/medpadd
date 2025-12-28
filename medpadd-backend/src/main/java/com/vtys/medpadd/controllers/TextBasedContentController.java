package com.vtys.medpadd.controllers;

import com.vtys.medpadd.entities.TextBasedContent;
import com.vtys.medpadd.services.TextBasedContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/text-contents")
@RequiredArgsConstructor
public class TextBasedContentController {

    private final TextBasedContentService service;

    @GetMapping
    public List<TextBasedContent> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TextBasedContent> getById(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TextBasedContent> create(@RequestBody TextBasedContent textBasedContent) {
        return ResponseEntity.ok(service.save(textBasedContent));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TextBasedContent> update(@PathVariable UUID id,
            @RequestBody TextBasedContent textBasedContent) {
        return service.findById(id)
                .map(existing -> {
                    textBasedContent.setId(existing.getId());
                    return ResponseEntity.ok(service.save(textBasedContent));
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
