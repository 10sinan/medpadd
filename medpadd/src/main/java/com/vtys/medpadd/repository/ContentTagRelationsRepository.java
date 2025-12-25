package com.vtys.medpadd.repository;

import com.vtys.medpadd.entity.ContentTagRelations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ContentTagRelationsRepository extends JpaRepository<ContentTagRelations, UUID> {
    List<ContentTagRelations> findByContentId(UUID contentId);

    List<ContentTagRelations> findByTagId(UUID tagId);
}
