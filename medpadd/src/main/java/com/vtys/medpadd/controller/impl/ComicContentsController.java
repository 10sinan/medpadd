package com.vtys.medpadd.controller.impl;

import com.vtys.medpadd.entity.ComicContents;
import com.vtys.medpadd.service.ComicContentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/comic-contents")
@RequiredArgsConstructor
public class ComicContentsController {

    private final ComicContentsService comicContentsService;

    @PostMapping
    public ComicContents create(@RequestBody ComicContents comicContent) {
        return comicContentsService.save(comicContent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComicContents> getById(@PathVariable UUID id) {
        return comicContentsService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<ComicContents> getAll() {
        return comicContentsService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        comicContentsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-content/{contentId}")
    public List<ComicContents> getByContent(@PathVariable UUID contentId) {
        return comicContentsService.findByContentId(contentId);
    }
}
