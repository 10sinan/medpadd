package com.vtys.medpadd.controller.impl;

import com.vtys.medpadd.entity.Complaints;
import com.vtys.medpadd.service.ComplaintsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/complaints")
@RequiredArgsConstructor
public class ComplaintsController {

    private final ComplaintsService complaintsService;

    @PostMapping
    public Complaints create(@RequestBody Complaints complaint) {
        return complaintsService.save(complaint);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Complaints> getById(@PathVariable UUID id) {
        return complaintsService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Complaints> getAll() {
        return complaintsService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        complaintsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-target")
    public List<Complaints> getByTarget(@RequestParam UUID targetId, @RequestParam String targetType) {
        return complaintsService.findByTargetIdAndTargetType(targetId, targetType);
    }

    @GetMapping("/by-user/{userId}")
    public List<Complaints> getByUser(@PathVariable UUID userId) {
        return complaintsService.findByUserId(userId);
    }
}
