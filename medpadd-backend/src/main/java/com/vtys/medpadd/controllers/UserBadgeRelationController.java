package com.vtys.medpadd.controllers;

import com.vtys.medpadd.entities.UserBadgeRelation;
import com.vtys.medpadd.entities.UserBadgeRelation.UserBadgeRelationId;
import com.vtys.medpadd.services.UserBadgeRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-badge-relations")
@RequiredArgsConstructor
public class UserBadgeRelationController {

    private final UserBadgeRelationService service;

    @GetMapping
    public List<UserBadgeRelation> getAll() {
        return service.findAll();
    }

    @GetMapping("/user/{userId}/badge/{badgeId}")
    public ResponseEntity<UserBadgeRelation> getById(@PathVariable UUID userId, @PathVariable UUID badgeId) {
        UserBadgeRelationId id = new UserBadgeRelationId(userId, badgeId);
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserBadgeRelation> create(@RequestBody UserBadgeRelation relation) {
        return ResponseEntity.ok(service.save(relation));
    }

    @PutMapping("/user/{userId}/badge/{badgeId}")
    public ResponseEntity<UserBadgeRelation> update(@PathVariable UUID userId, @PathVariable UUID badgeId,
            @RequestBody UserBadgeRelation relation) {
        UserBadgeRelationId id = new UserBadgeRelationId(userId, badgeId);
        if (service.findById(id).isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(service.save(relation));
    }

    @DeleteMapping("/user/{userId}/badge/{badgeId}")
    public ResponseEntity<Void> delete(@PathVariable UUID userId, @PathVariable UUID badgeId) {
        UserBadgeRelationId id = new UserBadgeRelationId(userId, badgeId);
        if (service.findById(id).isEmpty())
            return ResponseEntity.notFound().build();
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
