package com.vtys.medpadd.controllers;

import com.vtys.medpadd.entities.ComicPage;
import com.vtys.medpadd.services.ComicPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/comic-pages")
@RequiredArgsConstructor
public class ComicPageController {

    private final ComicPageService service;

    @GetMapping
    public List<ComicPage> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComicPage> getById(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ComicPage> create(@RequestBody ComicPage comicPage) {
        return ResponseEntity.ok(service.save(comicPage));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComicPage> update(@PathVariable UUID id, @RequestBody ComicPage comicPage) {
        return service.findById(id)
                .map(existing -> {
                    comicPage.setId(existing.getId());
                    return ResponseEntity.ok(service.save(comicPage));
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
