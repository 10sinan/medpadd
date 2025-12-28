package com.vtys.medpadd.controllers;

import com.vtys.medpadd.entities.JoinMembership;
import com.vtys.medpadd.services.JoinMembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/join-memberships")
@RequiredArgsConstructor
public class JoinMembershipController {

    private final JoinMembershipService service;

    @GetMapping
    public List<JoinMembership> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JoinMembership> getById(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<JoinMembership> create(@RequestBody JoinMembership membership) {
        return ResponseEntity.ok(service.save(membership));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JoinMembership> update(@PathVariable UUID id, @RequestBody JoinMembership membership) {
        return service.findById(id)
                .map(existing -> {
                    membership.setId(existing.getId());
                    return ResponseEntity.ok(service.save(membership));
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
