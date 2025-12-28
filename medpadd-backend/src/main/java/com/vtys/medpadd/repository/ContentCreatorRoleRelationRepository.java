package com.vtys.medpadd.repository;

import com.vtys.medpadd.entities.ContentCreatorRoleRelation;
import com.vtys.medpadd.entities.ContentCreatorRoleRelation.ContentCreatorRoleRelationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentCreatorRoleRelationRepository
        extends JpaRepository<ContentCreatorRoleRelation, ContentCreatorRoleRelationId> {
}
