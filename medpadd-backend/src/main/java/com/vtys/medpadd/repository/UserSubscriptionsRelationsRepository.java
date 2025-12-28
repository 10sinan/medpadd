package com.vtys.medpadd.repository;

import com.vtys.medpadd.entity.UserSubscriptionsRelations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserSubscriptionsRelationsRepository extends JpaRepository<UserSubscriptionsRelations, UUID> {
    List<UserSubscriptionsRelations> findByUserId(UUID userId);

    List<UserSubscriptionsRelations> findBySubscriptionId(UUID subscriptionId);
}
