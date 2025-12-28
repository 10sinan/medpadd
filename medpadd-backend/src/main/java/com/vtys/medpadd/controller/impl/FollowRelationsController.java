package com.vtys.medpadd.controller.impl;

import com.vtys.medpadd.entity.FollowRelations;
import com.vtys.medpadd.service.FollowRelationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/follow-relations")
@RequiredArgsConstructor
public class FollowRelationsController {

    private final FollowRelationsService followRelationsService;

    @PostMapping
    public FollowRelations create(@RequestBody FollowRelations relation) {
        return followRelationsService.save(relation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FollowRelations> getById(@PathVariable UUID id) {
        return followRelationsService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<FollowRelations> getAll() {
        return followRelationsService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        followRelationsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-content-creator/{contentCreatorId}")
    public List<FollowRelations> getByContentCreator(@PathVariable UUID contentCreatorId) {
        return followRelationsService.findByContentCreatorsId(contentCreatorId);
    }

    @GetMapping("/by-role/{roleId}")
    public List<FollowRelations> getByRole(@PathVariable UUID roleId) {
        return followRelationsService.findByComContentCreatorRolesId(roleId);
    }
}
