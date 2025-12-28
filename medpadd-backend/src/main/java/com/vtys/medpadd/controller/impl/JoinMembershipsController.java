package com.vtys.medpadd.controller.impl;

import com.vtys.medpadd.entity.JoinMemberships;
import com.vtys.medpadd.service.JoinMembershipsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/join-memberships")
@RequiredArgsConstructor
public class JoinMembershipsController {

    private final JoinMembershipsService joinMembershipsService;

    @PostMapping
    public JoinMemberships create(@RequestBody JoinMemberships joinMembership) {
        return joinMembershipsService.save(joinMembership);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JoinMemberships> getById(@PathVariable UUID id) {
        return joinMembershipsService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<JoinMemberships> getAll() {
        return joinMembershipsService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        joinMembershipsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-content-creator/{contentCreatorId}")
    public List<JoinMemberships> getByContentCreator(@PathVariable UUID contentCreatorId) {
        return joinMembershipsService.findByContentCreatorId(contentCreatorId);
    }
}
