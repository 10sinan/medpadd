package com.vtys.medpadd.repository;

import com.vtys.medpadd.entity.ContentCreatorTagsRelations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ContentCreatorTagsRelationsRepository extends JpaRepository<ContentCreatorTagsRelations, UUID> {
    List<ContentCreatorTagsRelations> findByContentCreatorId(UUID contentCreatorId);

    List<ContentCreatorTagsRelations> findByTagId(UUID tagId);
}
