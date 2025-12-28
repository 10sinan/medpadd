package com.vtys.medpadd.controller.impl;

import com.vtys.medpadd.entity.Content;
import com.vtys.medpadd.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/contents")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    @PostMapping
    public Content create(@RequestBody Content content) {
        return contentService.save(content);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Content> getById(@PathVariable UUID id) {
        return contentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Content> getAll() {
        return contentService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        contentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-content-creator/{contentCreatorId}")
    public List<Content> getByContentCreator(@PathVariable UUID contentCreatorId) {
        return contentService.findByContentCreatorId(contentCreatorId);
    }
}
