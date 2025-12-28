package com.vtys.medpadd.controller.impl;

import com.vtys.medpadd.entity.ContentTagRelations;
import com.vtys.medpadd.service.ContentTagRelationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/content-tag-relations")
@RequiredArgsConstructor
public class ContentTagRelationsController {

    private final ContentTagRelationsService contentTagRelationsService;

    @PostMapping
    public ContentTagRelations create(@RequestBody ContentTagRelations relation) {
        return contentTagRelationsService.save(relation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentTagRelations> getById(@PathVariable UUID id) {
        return contentTagRelationsService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<ContentTagRelations> getAll() {
        return contentTagRelationsService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        contentTagRelationsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-content/{contentId}")
    public List<ContentTagRelations> getByContent(@PathVariable UUID contentId) {
        return contentTagRelationsService.findByContentId(contentId);
    }

    @GetMapping("/by-tag/{tagId}")
    public List<ContentTagRelations> getByTag(@PathVariable UUID tagId) {
        return contentTagRelationsService.findByTagId(tagId);
    }
}
