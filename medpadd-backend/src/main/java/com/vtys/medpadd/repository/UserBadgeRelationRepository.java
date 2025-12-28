package com.vtys.medpadd.repository;

import com.vtys.medpadd.entities.UserBadgeRelation;
import com.vtys.medpadd.entities.UserBadgeRelation.UserBadgeRelationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBadgeRelationRepository extends JpaRepository<UserBadgeRelation, UserBadgeRelationId> {
}
