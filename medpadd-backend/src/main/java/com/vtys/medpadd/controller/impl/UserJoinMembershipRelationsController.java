package com.vtys.medpadd.controller.impl;

import com.vtys.medpadd.entity.UserJoinMembershipRelations;
import com.vtys.medpadd.service.UserJoinMembershipRelationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-join-membership-relations")
@RequiredArgsConstructor
public class UserJoinMembershipRelationsController {

    private final UserJoinMembershipRelationsService userJoinMembershipRelationsService;

    @PostMapping
    public UserJoinMembershipRelations create(@RequestBody UserJoinMembershipRelations relation) {
        return userJoinMembershipRelationsService.save(relation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserJoinMembershipRelations> getById(@PathVariable UUID id) {
        return userJoinMembershipRelationsService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<UserJoinMembershipRelations> getAll() {
        return userJoinMembershipRelationsService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userJoinMembershipRelationsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-user/{userId}")
    public List<UserJoinMembershipRelations> getByUserId(@PathVariable UUID userId) {
        return userJoinMembershipRelationsService.findByUserId(userId);
    }

    @GetMapping("/by-join-membership/{joinMembershipId}")
    public List<UserJoinMembershipRelations> getByJoinMembership(@PathVariable UUID joinMembershipId) {
        return userJoinMembershipRelationsService.findByJoinMembershipId(joinMembershipId);
    }
}
