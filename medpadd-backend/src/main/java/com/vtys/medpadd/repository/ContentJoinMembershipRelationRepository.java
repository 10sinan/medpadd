package com.vtys.medpadd.repository;

import com.vtys.medpadd.entities.ContentJoinMembershipRelation;
import com.vtys.medpadd.entities.ContentJoinMembershipRelation.ContentJoinMembershipRelationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentJoinMembershipRelationRepository
        extends JpaRepository<ContentJoinMembershipRelation, ContentJoinMembershipRelationId> {
}
