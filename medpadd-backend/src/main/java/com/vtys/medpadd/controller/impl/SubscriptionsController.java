package com.vtys.medpadd.controller.impl;

import com.vtys.medpadd.entity.Subscriptions;
import com.vtys.medpadd.service.SubscriptionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionsController {

    private final SubscriptionsService subscriptionsService;

    @PostMapping
    public Subscriptions create(@RequestBody Subscriptions subscription) {
        return subscriptionsService.save(subscription);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subscriptions> getById(@PathVariable UUID id) {
        return subscriptionsService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Subscriptions> getAll() {
        return subscriptionsService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        subscriptionsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<Subscriptions> getByName(@PathVariable String name) {
        return subscriptionsService.findByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
