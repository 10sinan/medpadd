package com.vtys.medpadd.controller.impl;

import com.vtys.medpadd.entity.UserBadgeRelations;
import com.vtys.medpadd.service.UserBadgeRelationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-badge-relations")
@RequiredArgsConstructor
public class UserBadgeRelationsController {

    private final UserBadgeRelationsService userBadgeRelationsService;

    @PostMapping
    public UserBadgeRelations create(@RequestBody UserBadgeRelations relation) {
        return userBadgeRelationsService.save(relation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserBadgeRelations> getById(@PathVariable UUID id) {
        return userBadgeRelationsService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<UserBadgeRelations> getAll() {
        return userBadgeRelationsService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userBadgeRelationsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-user/{userId}")
    public List<UserBadgeRelations> getByUserId(@PathVariable UUID userId) {
        return userBadgeRelationsService.findByUserId(userId);
    }

    @GetMapping("/by-badge/{badgeId}")
    public List<UserBadgeRelations> getByBadgeId(@PathVariable UUID badgeId) {
        return userBadgeRelationsService.findByBadgeId(badgeId);
    }
}
