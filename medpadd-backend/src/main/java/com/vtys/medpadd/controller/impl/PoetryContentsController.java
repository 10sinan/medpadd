package com.vtys.medpadd.controller.impl;

import com.vtys.medpadd.entity.PoetryContents;
import com.vtys.medpadd.service.PoetryContentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/poetry-contents")
@RequiredArgsConstructor
public class PoetryContentsController {

    private final PoetryContentsService poetryContentsService;

    @PostMapping
    public PoetryContents create(@RequestBody PoetryContents poetryContent) {
        return poetryContentsService.save(poetryContent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PoetryContents> getById(@PathVariable UUID id) {
        return poetryContentsService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<PoetryContents> getAll() {
        return poetryContentsService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        poetryContentsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-content/{contentId}")
    public List<PoetryContents> getByContent(@PathVariable UUID contentId) {
        return poetryContentsService.findByContentId(contentId);
    }
}
