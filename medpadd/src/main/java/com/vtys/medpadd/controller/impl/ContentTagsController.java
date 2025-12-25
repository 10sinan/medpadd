package com.vtys.medpadd.controller.impl;

import com.vtys.medpadd.entity.ContentTags;
import com.vtys.medpadd.service.ContentTagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/content-tags")
@RequiredArgsConstructor
public class ContentTagsController {

    private final ContentTagsService contentTagsService;

    @PostMapping
    public ContentTags create(@RequestBody ContentTags tag) {
        return contentTagsService.save(tag);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentTags> getById(@PathVariable UUID id) {
        return contentTagsService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<ContentTags> getAll() {
        return contentTagsService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        contentTagsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
