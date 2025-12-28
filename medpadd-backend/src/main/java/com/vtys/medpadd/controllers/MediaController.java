package com.vtys.medpadd.controllers;

import com.vtys.medpadd.entities.Media;
import com.vtys.medpadd.services.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService service;

    @GetMapping
    public List<Media> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Media> getById(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Media> create(@RequestBody Media media) {
        return ResponseEntity.ok(service.save(media));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Media> update(@PathVariable UUID id, @RequestBody Media media) {
        return service.findById(id)
                .map(existing -> {
                    media.setId(existing.getId());
                    return ResponseEntity.ok(service.save(media));
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
