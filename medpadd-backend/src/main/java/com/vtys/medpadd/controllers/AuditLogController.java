package com.vtys.medpadd.controllers;

import com.vtys.medpadd.entities.AuditLog;
import com.vtys.medpadd.services.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/audit-logs")
@RequiredArgsConstructor
public class AuditLogController {

    private final AuditLogService service;

    @GetMapping
    public List<AuditLog> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditLog> getById(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AuditLog> create(@RequestBody AuditLog auditLog) {
        return ResponseEntity.ok(service.save(auditLog));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuditLog> update(@PathVariable UUID id, @RequestBody AuditLog auditLog) {
        return service.findById(id)
                .map(existing -> {
                    auditLog.setId(existing.getId());
                    return ResponseEntity.ok(service.save(auditLog));
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
