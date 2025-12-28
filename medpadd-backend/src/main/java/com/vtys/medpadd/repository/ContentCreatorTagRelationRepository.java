package com.vtys.medpadd.repository;

import com.vtys.medpadd.entities.ContentCreatorTagRelation;
import com.vtys.medpadd.entities.ContentCreatorTagRelation.ContentCreatorTagRelationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentCreatorTagRelationRepository
        extends JpaRepository<ContentCreatorTagRelation, ContentCreatorTagRelationId> {
}
