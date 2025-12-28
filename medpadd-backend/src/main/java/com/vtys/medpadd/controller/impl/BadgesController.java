package com.vtys.medpadd.controller.impl;

import com.vtys.medpadd.entity.Badges;
import com.vtys.medpadd.service.BadgesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/badges")
@RequiredArgsConstructor
public class BadgesController {

    private final BadgesService badgesService;

    @PostMapping
    public Badges create(@RequestBody Badges badge) {
        return badgesService.save(badge);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Badges> getById(@PathVariable UUID id) {
        return badgesService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Badges> getAll() {
        return badgesService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        badgesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-code/{code}")
    public ResponseEntity<Badges> getByCode(@PathVariable String code) {
        return badgesService.findByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
