package com.vtys.medpadd.service;

import com.vtys.medpadd.entity.UserSubscriptionsRelations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserSubscriptionsRelationsService {
    UserSubscriptionsRelations save(UserSubscriptionsRelations relation);

    Optional<UserSubscriptionsRelations> findById(UUID id);

    List<UserSubscriptionsRelations> findAll();

    void deleteById(UUID id);

    List<UserSubscriptionsRelations> findByUserId(UUID userId);

    List<UserSubscriptionsRelations> findBySubscriptionId(UUID subscriptionId);
}
