package com.vtys.medpadd.controller.impl;

import com.vtys.medpadd.entity.ComicPages;
import com.vtys.medpadd.service.ComicPagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/comic-pages")
@RequiredArgsConstructor
public class ComicPagesController {

    private final ComicPagesService comicPagesService;

    @PostMapping
    public ComicPages create(@RequestBody ComicPages comicPage) {
        return comicPagesService.save(comicPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComicPages> getById(@PathVariable UUID id) {
        return comicPagesService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<ComicPages> getAll() {
        return comicPagesService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        comicPagesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-comic/{comicId}")
    public List<ComicPages> getByComic(@PathVariable UUID comicId) {
        return comicPagesService.findByComicIdOrderByPageAsc(comicId);
    }
}
