package com.vtys.medpadd.controller.impl;

import com.vtys.medpadd.entity.SystemRoles;
import com.vtys.medpadd.service.SystemRolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/system-roles")
@RequiredArgsConstructor
public class SystemRolesController {

    private final SystemRolesService systemRolesService;

    @PostMapping
    public SystemRoles create(@RequestBody SystemRoles role) {
        return systemRolesService.save(role);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SystemRoles> getById(@PathVariable UUID id) {
        return systemRolesService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<SystemRoles> getAll() {
        return systemRolesService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        systemRolesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-name/{roleName}")
    public ResponseEntity<SystemRoles> getByRoleName(@PathVariable String roleName) {
        return systemRolesService.findByRoleName(roleName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
