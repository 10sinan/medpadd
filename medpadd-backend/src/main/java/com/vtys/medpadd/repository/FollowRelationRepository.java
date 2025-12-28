package com.vtys.medpadd.repository;

import com.vtys.medpadd.entities.FollowRelation;
import com.vtys.medpadd.entities.FollowRelation.FollowRelationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRelationRepository extends JpaRepository<FollowRelation, FollowRelationId> {
}
