package com.vtys.medpadd.controllers;

import com.vtys.medpadd.entities.ContentCreatorRole;
import com.vtys.medpadd.services.ContentCreatorRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/content-creator-roles")
@RequiredArgsConstructor
public class ContentCreatorRoleController {

    private final ContentCreatorRoleService service;

    @GetMapping
    public List<ContentCreatorRole> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentCreatorRole> getById(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ContentCreatorRole> create(@RequestBody ContentCreatorRole role) {
        return ResponseEntity.ok(service.save(role));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContentCreatorRole> update(@PathVariable UUID id, @RequestBody ContentCreatorRole role) {
        return service.findById(id)
                .map(existing -> {
                    role.setId(existing.getId());
                    return ResponseEntity.ok(service.save(role));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (service.findById(id).isEmpty())
            return ResponseEntity.notFound().build();
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
