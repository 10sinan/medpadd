package com.vtys.medpadd.controller.impl;

import com.vtys.medpadd.entity.ContentCreatorTags;
import com.vtys.medpadd.service.ContentCreatorTagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/content-creator-tags")
@RequiredArgsConstructor
public class ContentCreatorTagsController {

    private final ContentCreatorTagsService contentCreatorTagsService;

    @PostMapping
    public ContentCreatorTags create(@RequestBody ContentCreatorTags tag) {
        return contentCreatorTagsService.save(tag);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentCreatorTags> getById(@PathVariable UUID id) {
        return contentCreatorTagsService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<ContentCreatorTags> getAll() {
        return contentCreatorTagsService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        contentCreatorTagsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<ContentCreatorTags> getByName(@PathVariable String name) {
        return contentCreatorTagsService.findByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
