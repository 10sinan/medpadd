package com.vtys.medpadd.controller.impl;

import com.vtys.medpadd.entity.ContentCreatorTagsRelations;
import com.vtys.medpadd.service.ContentCreatorTagsRelationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/content-creator-tags-relations")
@RequiredArgsConstructor
public class ContentCreatorTagsRelationsController {

    private final ContentCreatorTagsRelationsService contentCreatorTagsRelationsService;

    @PostMapping
    public ContentCreatorTagsRelations create(@RequestBody ContentCreatorTagsRelations relation) {
        return contentCreatorTagsRelationsService.save(relation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentCreatorTagsRelations> getById(@PathVariable UUID id) {
        return contentCreatorTagsRelationsService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<ContentCreatorTagsRelations> getAll() {
        return contentCreatorTagsRelationsService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        contentCreatorTagsRelationsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-content-creator/{contentCreatorId}")
    public List<ContentCreatorTagsRelations> getByContentCreator(@PathVariable UUID contentCreatorId) {
        return contentCreatorTagsRelationsService.findByContentCreatorId(contentCreatorId);
    }

    @GetMapping("/by-tag/{tagId}")
    public List<ContentCreatorTagsRelations> getByTag(@PathVariable UUID tagId) {
        return contentCreatorTagsRelationsService.findByTagId(tagId);
    }
}
