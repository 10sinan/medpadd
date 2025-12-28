package com.vtys.medpadd.controllers;

import com.vtys.medpadd.entities.UserJoinMembershipRelation;
import com.vtys.medpadd.entities.UserJoinMembershipRelation.UserJoinMembershipRelationId;
import com.vtys.medpadd.services.UserJoinMembershipRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-join-membership-relations")
@RequiredArgsConstructor
public class UserJoinMembershipRelationController {

    private final UserJoinMembershipRelationService service;

    @GetMapping
    public List<UserJoinMembershipRelation> getAll() {
        return service.findAll();
    }

    @GetMapping("/user/{userId}/membership/{membershipId}")
    public ResponseEntity<UserJoinMembershipRelation> getById(@PathVariable UUID userId,
            @PathVariable UUID membershipId) {
        UserJoinMembershipRelationId id = new UserJoinMembershipRelationId(userId, membershipId);
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserJoinMembershipRelation> create(@RequestBody UserJoinMembershipRelation relation) {
        return ResponseEntity.ok(service.save(relation));
    }

    @PutMapping("/user/{userId}/membership/{membershipId}")
    public ResponseEntity<UserJoinMembershipRelation> update(@PathVariable UUID userId, @PathVariable UUID membershipId,
            @RequestBody UserJoinMembershipRelation relation) {
        UserJoinMembershipRelationId id = new UserJoinMembershipRelationId(userId, membershipId);
        if (service.findById(id).isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(service.save(relation));
    }

    @DeleteMapping("/user/{userId}/membership/{membershipId}")
    public ResponseEntity<Void> delete(@PathVariable UUID userId, @PathVariable UUID membershipId) {
        UserJoinMembershipRelationId id = new UserJoinMembershipRelationId(userId, membershipId);
        if (service.findById(id).isEmpty())
            return ResponseEntity.notFound().build();
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
