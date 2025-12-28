package com.vtys.medpadd.controllers;

import com.vtys.medpadd.entities.ContentCreatorVerificationRelation;
import com.vtys.medpadd.entities.ContentCreatorVerificationRelation.ContentCreatorVerificationRelationId;
import com.vtys.medpadd.services.ContentCreatorVerificationRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/content-creator-verification-relations")
@RequiredArgsConstructor
public class ContentCreatorVerificationRelationController {

    private final ContentCreatorVerificationRelationService service;

    @GetMapping
    public List<ContentCreatorVerificationRelation> getAll() {
        return service.findAll();
    }

    @GetMapping("/badge/{badgeId}/creator/{creatorId}")
    public ResponseEntity<ContentCreatorVerificationRelation> getById(@PathVariable UUID badgeId,
            @PathVariable UUID creatorId) {
        ContentCreatorVerificationRelationId id = new ContentCreatorVerificationRelationId(badgeId, creatorId);
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ContentCreatorVerificationRelation> create(
            @RequestBody ContentCreatorVerificationRelation relation) {
        return ResponseEntity.ok(service.save(relation));
    }

    @DeleteMapping("/badge/{badgeId}/creator/{creatorId}")
    public ResponseEntity<Void> delete(@PathVariable UUID badgeId, @PathVariable UUID creatorId) {
        ContentCreatorVerificationRelationId id = new ContentCreatorVerificationRelationId(badgeId, creatorId);
        if (service.findById(id).isEmpty())
            return ResponseEntity.notFound().build();
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
