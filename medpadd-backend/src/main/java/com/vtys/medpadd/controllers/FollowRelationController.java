package com.vtys.medpadd.controllers;

import com.vtys.medpadd.entities.FollowRelation;
import com.vtys.medpadd.entities.FollowRelation.FollowRelationId;
import com.vtys.medpadd.services.FollowRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/follows")
@RequiredArgsConstructor
public class FollowRelationController {

    private final FollowRelationService service;

    @GetMapping
    public List<FollowRelation> getAll() {
        return service.findAll();
    }

    @GetMapping("/user/{userId}/creator/{creatorId}")
    public ResponseEntity<FollowRelation> getById(@PathVariable UUID userId, @PathVariable UUID creatorId) {
        FollowRelationId id = new FollowRelationId(userId, creatorId);
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FollowRelation> create(@RequestBody FollowRelation followRelation) {
        return ResponseEntity.ok(service.save(followRelation));
    }

    @DeleteMapping("/user/{userId}/creator/{creatorId}")
    public ResponseEntity<Void> delete(@PathVariable UUID userId, @PathVariable UUID creatorId) {
        FollowRelationId id = new FollowRelationId(userId, creatorId);
        if (service.findById(id).isEmpty())
            return ResponseEntity.notFound().build();
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
