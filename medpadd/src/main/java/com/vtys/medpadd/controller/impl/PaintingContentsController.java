package com.vtys.medpadd.controller.impl;

import com.vtys.medpadd.entity.PaintingContents;
import com.vtys.medpadd.service.PaintingContentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/painting-contents")
@RequiredArgsConstructor
public class PaintingContentsController {

    private final PaintingContentsService paintingContentsService;

    @PostMapping
    public PaintingContents create(@RequestBody PaintingContents paintingContent) {
        return paintingContentsService.save(paintingContent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaintingContents> getById(@PathVariable UUID id) {
        return paintingContentsService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<PaintingContents> getAll() {
        return paintingContentsService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        paintingContentsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-content/{contentId}")
    public List<PaintingContents> getByContent(@PathVariable UUID contentId) {
        return paintingContentsService.findByContentId(contentId);
    }
}
