package com.vtys.medpadd.controllers;

import com.vtys.medpadd.entities.ContentTagRelation;
import com.vtys.medpadd.entities.ContentTagRelation.ContentTagRelationId;
import com.vtys.medpadd.services.ContentTagRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/content-tag-relations")
@RequiredArgsConstructor
public class ContentTagRelationController {

    private final ContentTagRelationService service;

    @GetMapping
    public List<ContentTagRelation> getAll() {
        return service.findAll();
    }

    @GetMapping("/content/{contentId}/tag/{tagId}")
    public ResponseEntity<ContentTagRelation> getById(@PathVariable UUID contentId, @PathVariable UUID tagId) {
        ContentTagRelationId id = new ContentTagRelationId(contentId, tagId);
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ContentTagRelation> create(@RequestBody ContentTagRelation relation) {
        return ResponseEntity.ok(service.save(relation));
    }

    @DeleteMapping("/content/{contentId}/tag/{tagId}")
    public ResponseEntity<Void> delete(@PathVariable UUID contentId, @PathVariable UUID tagId) {
        ContentTagRelationId id = new ContentTagRelationId(contentId, tagId);
        if (service.findById(id).isEmpty())
            return ResponseEntity.notFound().build();
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
