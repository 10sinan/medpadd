package com.vtys.medpadd.controllers;

import com.vtys.medpadd.entities.SystemRole;
import com.vtys.medpadd.services.SystemRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/system-roles")
@RequiredArgsConstructor
public class SystemRoleController {

    private final SystemRoleService service;

    @GetMapping
    public List<SystemRole> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SystemRole> getById(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SystemRole> create(@RequestBody SystemRole role) {
        return ResponseEntity.ok(service.save(role));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SystemRole> update(@PathVariable UUID id, @RequestBody SystemRole role) {
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
