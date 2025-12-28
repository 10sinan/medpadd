package com.vtys.medpadd.repository;

import com.vtys.medpadd.entities.UserJoinMembershipRelation;
import com.vtys.medpadd.entities.UserJoinMembershipRelation.UserJoinMembershipRelationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJoinMembershipRelationRepository
        extends JpaRepository<UserJoinMembershipRelation, UserJoinMembershipRelationId> {
}
