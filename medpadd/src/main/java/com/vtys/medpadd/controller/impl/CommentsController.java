package com.vtys.medpadd.controller.impl;

import com.vtys.medpadd.entity.Comments;
import com.vtys.medpadd.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentsController {

    private final CommentsService commentsService;

    @PostMapping
    public Comments create(@RequestBody Comments comment) {
        return commentsService.save(comment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comments> getById(@PathVariable UUID id) {
        return commentsService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Comments> getAll() {
        return commentsService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        commentsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-content/{contentId}")
    public List<Comments> getByContent(@PathVariable UUID contentId) {
        return commentsService.findByContentId(contentId);
    }

    @GetMapping("/by-user/{userId}")
    public List<Comments> getByUser(@PathVariable UUID userId) {
        return commentsService.findByUserId(userId);
    }
}
