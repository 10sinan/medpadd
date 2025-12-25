package com.vtys.medpadd.controller.impl;

import com.vtys.medpadd.entity.Media;
import com.vtys.medpadd.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    @PostMapping
    public Media create(@RequestBody Media media) {
        return mediaService.save(media);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Media> getById(@PathVariable UUID id) {
        return mediaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Media> getAll() {
        return mediaService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        mediaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-user/{userId}")
    public List<Media> getByUserId(@PathVariable UUID userId) {
        return mediaService.findByUserId(userId);
    }
}
