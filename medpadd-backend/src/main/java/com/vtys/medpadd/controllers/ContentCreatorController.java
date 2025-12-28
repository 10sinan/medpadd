package com.vtys.medpadd.controllers;

import com.vtys.medpadd.entities.ContentCreator;
import com.vtys.medpadd.services.ContentCreatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/content-creators")
@RequiredArgsConstructor
public class ContentCreatorController {

    private final ContentCreatorService service;

    @GetMapping
    public List<ContentCreator> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentCreator> getById(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ContentCreator> create(@RequestBody ContentCreator creator) {
        return ResponseEntity.ok(service.save(creator));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContentCreator> update(@PathVariable UUID id, @RequestBody ContentCreator creator) {
        return service.findById(id)
                .map(existing -> {
                    creator.setId(existing.getId());
                    return ResponseEntity.ok(service.save(creator));
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
