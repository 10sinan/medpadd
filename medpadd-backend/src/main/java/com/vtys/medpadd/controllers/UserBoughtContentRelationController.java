package com.vtys.medpadd.controllers;

import com.vtys.medpadd.entities.UserBoughtContentRelation;
import com.vtys.medpadd.entities.UserBoughtContentRelation.UserBoughtContentRelationId;
import com.vtys.medpadd.services.UserBoughtContentRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-bought-content-relations")
@RequiredArgsConstructor
public class UserBoughtContentRelationController {

    private final UserBoughtContentRelationService service;

    @GetMapping
    public List<UserBoughtContentRelation> getAll() {
        return service.findAll();
    }

    @GetMapping("/user/{userId}/content/{contentId}")
    public ResponseEntity<UserBoughtContentRelation> getById(@PathVariable UUID userId, @PathVariable UUID contentId) {
        UserBoughtContentRelationId id = new UserBoughtContentRelationId(userId, contentId);
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserBoughtContentRelation> create(@RequestBody UserBoughtContentRelation relation) {
        return ResponseEntity.ok(service.save(relation));
    }

    @DeleteMapping("/user/{userId}/content/{contentId}")
    public ResponseEntity<Void> delete(@PathVariable UUID userId, @PathVariable UUID contentId) {
        UserBoughtContentRelationId id = new UserBoughtContentRelationId(userId, contentId);
        if (service.findById(id).isEmpty())
            return ResponseEntity.notFound().build();
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
