package com.vtys.medpadd.controller.impl;

import com.vtys.medpadd.entity.ContentCreatorVerificationsBadges;
import com.vtys.medpadd.service.ContentCreatorVerificationsBadgesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/content-creator-verifications-badges")
@RequiredArgsConstructor
public class ContentCreatorVerificationsBadgesController {

    private final ContentCreatorVerificationsBadgesService contentCreatorVerificationsBadgesService;

    @PostMapping
    public ContentCreatorVerificationsBadges create(@RequestBody ContentCreatorVerificationsBadges badge) {
        return contentCreatorVerificationsBadgesService.save(badge);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentCreatorVerificationsBadges> getById(@PathVariable UUID id) {
        return contentCreatorVerificationsBadgesService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<ContentCreatorVerificationsBadges> getAll() {
        return contentCreatorVerificationsBadgesService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        contentCreatorVerificationsBadgesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-code/{code}")
    public ResponseEntity<ContentCreatorVerificationsBadges> getByCode(@PathVariable String code) {
        return contentCreatorVerificationsBadgesService.findByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
