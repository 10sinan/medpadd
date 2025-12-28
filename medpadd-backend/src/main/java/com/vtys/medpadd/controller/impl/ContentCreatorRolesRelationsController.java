package com.vtys.medpadd.controller.impl;

import com.vtys.medpadd.entity.ContentCreatorRolesRelations;
import com.vtys.medpadd.service.ContentCreatorRolesRelationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/content-creator-roles-relations")
@RequiredArgsConstructor
public class ContentCreatorRolesRelationsController {

    private final ContentCreatorRolesRelationsService contentCreatorRolesRelationsService;

    @PostMapping
    public ContentCreatorRolesRelations create(@RequestBody ContentCreatorRolesRelations relation) {
        return contentCreatorRolesRelationsService.save(relation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentCreatorRolesRelations> getById(@PathVariable UUID id) {
        return contentCreatorRolesRelationsService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<ContentCreatorRolesRelations> getAll() {
        return contentCreatorRolesRelationsService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        contentCreatorRolesRelationsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-content-creator/{contentCreatorId}")
    public List<ContentCreatorRolesRelations> getByContentCreator(@PathVariable UUID contentCreatorId) {
        return contentCreatorRolesRelationsService.findByContentCreatorsId(contentCreatorId);
    }

    @GetMapping("/by-role/{roleId}")
    public List<ContentCreatorRolesRelations> getByRole(@PathVariable UUID roleId) {
        return contentCreatorRolesRelationsService.findByContentCreatorsRolesId(roleId);
    }
}
