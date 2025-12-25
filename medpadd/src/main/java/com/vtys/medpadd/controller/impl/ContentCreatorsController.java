package com.vtys.medpadd.controller.impl;

import com.vtys.medpadd.entity.ContentCreators;
import com.vtys.medpadd.service.ContentCreatorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/content-creators")
@RequiredArgsConstructor
public class ContentCreatorsController {

    private final ContentCreatorsService contentCreatorsService;

    @PostMapping
    public ContentCreators create(@RequestBody ContentCreators contentCreator) {
        return contentCreatorsService.save(contentCreator);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentCreators> getById(@PathVariable UUID id) {
        return contentCreatorsService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<ContentCreators> getAll() {
        return contentCreatorsService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        contentCreatorsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<ContentCreators> getByUserId(@PathVariable UUID userId) {
        return contentCreatorsService.findByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
