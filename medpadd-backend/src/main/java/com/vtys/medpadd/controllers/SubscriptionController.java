package com.vtys.medpadd.controllers;

import com.vtys.medpadd.entities.Subscription;
import com.vtys.medpadd.services.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService service;

    @GetMapping
    public List<Subscription> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getById(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Subscription> create(@RequestBody Subscription subscription) {
        return ResponseEntity.ok(service.save(subscription));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subscription> update(@PathVariable UUID id, @RequestBody Subscription subscription) {
        return service.findById(id)
                .map(existing -> {
                    subscription.setId(existing.getId());
                    return ResponseEntity.ok(service.save(subscription));
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
