package com.vtys.medpadd.repository;

import com.vtys.medpadd.entities.ContentCreatorVerificationRelation;
import com.vtys.medpadd.entities.ContentCreatorVerificationRelation.ContentCreatorVerificationRelationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentCreatorVerificationRelationRepository
        extends JpaRepository<ContentCreatorVerificationRelation, ContentCreatorVerificationRelationId> {
}
