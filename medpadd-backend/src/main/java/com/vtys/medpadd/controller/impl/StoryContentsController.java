package com.vtys.medpadd.controller.impl;

import com.vtys.medpadd.entity.StoryContents;
import com.vtys.medpadd.service.StoryContentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/story-contents")
@RequiredArgsConstructor
public class StoryContentsController {

    private final StoryContentsService storyContentsService;

    @PostMapping
    public StoryContents create(@RequestBody StoryContents storyContent) {
        return storyContentsService.save(storyContent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoryContents> getById(@PathVariable UUID id) {
        return storyContentsService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<StoryContents> getAll() {
        return storyContentsService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        storyContentsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-content/{contentId}")
    public List<StoryContents> getByContent(@PathVariable UUID contentId) {
        return storyContentsService.findByContentId(contentId);
    }
}
