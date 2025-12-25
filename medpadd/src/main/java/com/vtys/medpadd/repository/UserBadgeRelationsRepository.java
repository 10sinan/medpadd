package com.vtys.medpadd.repository;

import com.vtys.medpadd.entity.UserBadgeRelations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserBadgeRelationsRepository extends JpaRepository<UserBadgeRelations, UUID> {
    List<UserBadgeRelations> findByUserId(UUID userId);

    List<UserBadgeRelations> findByBadgeId(UUID badgeId);
}
