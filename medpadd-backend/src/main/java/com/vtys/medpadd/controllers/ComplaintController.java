package com.vtys.medpadd.controllers;

import com.vtys.medpadd.entities.Complaint;
import com.vtys.medpadd.services.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/complaints")
@RequiredArgsConstructor
public class ComplaintController {

    private final ComplaintService service;

    @GetMapping
    public List<Complaint> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Complaint> getById(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Complaint> create(@RequestBody Complaint complaint) {
        return ResponseEntity.ok(service.save(complaint));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Complaint> update(@PathVariable UUID id, @RequestBody Complaint complaint) {
        return service.findById(id)
                .map(existing -> {
                    complaint.setId(existing.getId());
                    return ResponseEntity.ok(service.save(complaint));
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
