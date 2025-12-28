package com.vtys.medpadd.repository;

import com.vtys.medpadd.entity.UserJoinMembershipRelations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserJoinMembershipRelationsRepository extends JpaRepository<UserJoinMembershipRelations, UUID> {
    List<UserJoinMembershipRelations> findByUserId(UUID userId);

    List<UserJoinMembershipRelations> findByJoinMembershipId(UUID joinMembershipId);
}
