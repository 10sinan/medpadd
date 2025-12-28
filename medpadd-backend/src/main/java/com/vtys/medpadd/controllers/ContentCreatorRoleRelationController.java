package com.vtys.medpadd.controllers;

import com.vtys.medpadd.entities.ContentCreatorRoleRelation;
import com.vtys.medpadd.entities.ContentCreatorRoleRelation.ContentCreatorRoleRelationId;
import com.vtys.medpadd.services.ContentCreatorRoleRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/content-creator-role-relations")
@RequiredArgsConstructor
public class ContentCreatorRoleRelationController {

    private final ContentCreatorRoleRelationService service;

    @GetMapping
    public List<ContentCreatorRoleRelation> getAll() {
        return service.findAll();
    }

    @GetMapping("/role/{roleId}/creator/{creatorId}")
    public ResponseEntity<ContentCreatorRoleRelation> getById(@PathVariable UUID roleId, @PathVariable UUID creatorId) {
        ContentCreatorRoleRelationId id = new ContentCreatorRoleRelationId(roleId, creatorId);
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ContentCreatorRoleRelation> create(@RequestBody ContentCreatorRoleRelation relation) {
        return ResponseEntity.ok(service.save(relation));
    }

    @DeleteMapping("/role/{roleId}/creator/{creatorId}")
    public ResponseEntity<Void> delete(@PathVariable UUID roleId, @PathVariable UUID creatorId) {
        ContentCreatorRoleRelationId id = new ContentCreatorRoleRelationId(roleId, creatorId);
        if (service.findById(id).isEmpty())
            return ResponseEntity.notFound().build();
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
