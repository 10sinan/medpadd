package com.vtys.medpadd.controllers;

import com.vtys.medpadd.entities.UserSubscriptionRelation;
import com.vtys.medpadd.entities.UserSubscriptionRelation.UserSubscriptionRelationId;
import com.vtys.medpadd.services.UserSubscriptionRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-subscription-relations")
@RequiredArgsConstructor
public class UserSubscriptionRelationController {

    private final UserSubscriptionRelationService service;

    @GetMapping
    public List<UserSubscriptionRelation> getAll() {
        return service.findAll();
    }

    @GetMapping("/user/{userId}/subscription/{subscriptionId}")
    public ResponseEntity<UserSubscriptionRelation> getById(@PathVariable UUID userId,
            @PathVariable UUID subscriptionId) {
        UserSubscriptionRelationId id = new UserSubscriptionRelationId(userId, subscriptionId);
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserSubscriptionRelation> create(@RequestBody UserSubscriptionRelation relation) {
        return ResponseEntity.ok(service.save(relation));
    }

    @PutMapping("/user/{userId}/subscription/{subscriptionId}")
    public ResponseEntity<UserSubscriptionRelation> update(@PathVariable UUID userId, @PathVariable UUID subscriptionId,
            @RequestBody UserSubscriptionRelation relation) {
        UserSubscriptionRelationId id = new UserSubscriptionRelationId(userId, subscriptionId);
        if (service.findById(id).isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(service.save(relation));
    }

    @DeleteMapping("/user/{userId}/subscription/{subscriptionId}")
    public ResponseEntity<Void> delete(@PathVariable UUID userId, @PathVariable UUID subscriptionId) {
        UserSubscriptionRelationId id = new UserSubscriptionRelationId(userId, subscriptionId);
        if (service.findById(id).isEmpty())
            return ResponseEntity.notFound().build();
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
