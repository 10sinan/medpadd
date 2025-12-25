package com.vtys.medpadd.controller.impl;

import com.vtys.medpadd.entity.UserSubscriptionsRelations;
import com.vtys.medpadd.service.UserSubscriptionsRelationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-subscription-relations")
@RequiredArgsConstructor
public class UserSubscriptionsRelationsController {

    private final UserSubscriptionsRelationsService userSubscriptionsRelationsService;

    @PostMapping
    public UserSubscriptionsRelations create(@RequestBody UserSubscriptionsRelations relation) {
        return userSubscriptionsRelationsService.save(relation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserSubscriptionsRelations> getById(@PathVariable UUID id) {
        return userSubscriptionsRelationsService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<UserSubscriptionsRelations> getAll() {
        return userSubscriptionsRelationsService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userSubscriptionsRelationsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-user/{userId}")
    public List<UserSubscriptionsRelations> getByUserId(@PathVariable UUID userId) {
        return userSubscriptionsRelationsService.findByUserId(userId);
    }

    @GetMapping("/by-subscription/{subscriptionId}")
    public List<UserSubscriptionsRelations> getBySubscriptionId(@PathVariable UUID subscriptionId) {
        return userSubscriptionsRelationsService.findBySubscriptionId(subscriptionId);
    }
}
