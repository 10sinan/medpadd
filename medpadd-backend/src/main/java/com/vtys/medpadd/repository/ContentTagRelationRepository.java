package com.vtys.medpadd.repository;

import com.vtys.medpadd.entities.ContentTagRelation;
import com.vtys.medpadd.entities.ContentTagRelation.ContentTagRelationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentTagRelationRepository extends JpaRepository<ContentTagRelation, ContentTagRelationId> {
}
