package com.vtys.medpadd.controllers;

import com.vtys.medpadd.entities.PaintingContent;
import com.vtys.medpadd.services.PaintingContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/painting-contents")
@RequiredArgsConstructor
public class PaintingContentController {

    private final PaintingContentService service;

    @GetMapping
    public List<PaintingContent> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaintingContent> getById(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PaintingContent> create(@RequestBody PaintingContent paintingContent) {
        return ResponseEntity.ok(service.save(paintingContent));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaintingContent> update(@PathVariable UUID id, @RequestBody PaintingContent paintingContent) {
        return service.findById(id)
                .map(existing -> {
                    paintingContent.setId(existing.getId());
                    return ResponseEntity.ok(service.save(paintingContent));
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
