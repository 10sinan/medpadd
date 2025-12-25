package com.vtys.medpadd.service;

import com.vtys.medpadd.entity.UserBadgeRelations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserBadgeRelationsService {
    UserBadgeRelations save(UserBadgeRelations relation);

    Optional<UserBadgeRelations> findById(UUID id);

    List<UserBadgeRelations> findAll();

    void deleteById(UUID id);

    List<UserBadgeRelations> findByUserId(UUID userId);

    List<UserBadgeRelations> findByBadgeId(UUID badgeId);
}
