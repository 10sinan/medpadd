package com.vtys.medpadd.controller.impl;

import com.vtys.medpadd.entity.ContentCreatorRoles;
import com.vtys.medpadd.service.ContentCreatorRolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/content-creator-roles")
@RequiredArgsConstructor
public class ContentCreatorRolesController {

    private final ContentCreatorRolesService contentCreatorRolesService;

    @PostMapping
    public ContentCreatorRoles create(@RequestBody ContentCreatorRoles role) {
        return contentCreatorRolesService.save(role);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentCreatorRoles> getById(@PathVariable UUID id) {
        return contentCreatorRolesService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<ContentCreatorRoles> getAll() {
        return contentCreatorRolesService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        contentCreatorRolesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-code/{code}")
    public ResponseEntity<ContentCreatorRoles> getByCode(@PathVariable String code) {
        return contentCreatorRolesService.findByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
