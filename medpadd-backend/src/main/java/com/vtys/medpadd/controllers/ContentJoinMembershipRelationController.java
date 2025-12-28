package com.vtys.medpadd.controllers;

import com.vtys.medpadd.entities.ContentJoinMembershipRelation;
import com.vtys.medpadd.entities.ContentJoinMembershipRelation.ContentJoinMembershipRelationId;
import com.vtys.medpadd.services.ContentJoinMembershipRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/content-join-membership-relations")
@RequiredArgsConstructor
public class ContentJoinMembershipRelationController {

    private final ContentJoinMembershipRelationService service;

    @GetMapping
    public List<ContentJoinMembershipRelation> getAll() {
        return service.findAll();
    }

    @GetMapping("/membership/{membershipId}/content/{contentId}")
    public ResponseEntity<ContentJoinMembershipRelation> getById(@PathVariable UUID membershipId,
            @PathVariable UUID contentId) {
        ContentJoinMembershipRelationId id = new ContentJoinMembershipRelationId(membershipId, contentId);
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ContentJoinMembershipRelation> create(@RequestBody ContentJoinMembershipRelation relation) {
        return ResponseEntity.ok(service.save(relation));
    }

    @DeleteMapping("/membership/{membershipId}/content/{contentId}")
    public ResponseEntity<Void> delete(@PathVariable UUID membershipId, @PathVariable UUID contentId) {
        ContentJoinMembershipRelationId id = new ContentJoinMembershipRelationId(membershipId, contentId);
        if (service.findById(id).isEmpty())
            return ResponseEntity.notFound().build();
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
