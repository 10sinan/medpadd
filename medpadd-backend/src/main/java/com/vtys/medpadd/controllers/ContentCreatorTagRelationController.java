package com.vtys.medpadd.controllers;

import com.vtys.medpadd.entities.ContentCreatorTagRelation;
import com.vtys.medpadd.entities.ContentCreatorTagRelation.ContentCreatorTagRelationId;
import com.vtys.medpadd.services.ContentCreatorTagRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/content-creator-tag-relations")
@RequiredArgsConstructor
public class ContentCreatorTagRelationController {

    private final ContentCreatorTagRelationService service;

    @GetMapping
    public List<ContentCreatorTagRelation> getAll() {
        return service.findAll();
    }

    @GetMapping("/tag/{tagId}/creator/{creatorId}")
    public ResponseEntity<ContentCreatorTagRelation> getById(@PathVariable UUID tagId, @PathVariable UUID creatorId) {
        ContentCreatorTagRelationId id = new ContentCreatorTagRelationId(tagId, creatorId);
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ContentCreatorTagRelation> create(@RequestBody ContentCreatorTagRelation relation) {
        return ResponseEntity.ok(service.save(relation));
    }

    @DeleteMapping("/tag/{tagId}/creator/{creatorId}")
    public ResponseEntity<Void> delete(@PathVariable UUID tagId, @PathVariable UUID creatorId) {
        ContentCreatorTagRelationId id = new ContentCreatorTagRelationId(tagId, creatorId);
        if (service.findById(id).isEmpty())
            return ResponseEntity.notFound().build();
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
