package com.vtys.medpadd.service;

import com.vtys.medpadd.entity.UserJoinMembershipRelations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserJoinMembershipRelationsService {
    UserJoinMembershipRelations save(UserJoinMembershipRelations relation);

    Optional<UserJoinMembershipRelations> findById(UUID id);

    List<UserJoinMembershipRelations> findAll();

    void deleteById(UUID id);

    List<UserJoinMembershipRelations> findByUserId(UUID userId);

    List<UserJoinMembershipRelations> findByJoinMembershipId(UUID joinMembershipId);
}
