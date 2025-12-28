package com.vtys.medpadd.service.impl;

import com.vtys.medpadd.entity.UserSubscriptionsRelations;
import com.vtys.medpadd.repository.UserSubscriptionsRelationsRepository;
import com.vtys.medpadd.service.UserSubscriptionsRelationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserSubscriptionsRelationsServiceImpl implements UserSubscriptionsRelationsService {

    private final UserSubscriptionsRelationsRepository userSubscriptionsRelationsRepository;

    @Override
    public UserSubscriptionsRelations save(UserSubscriptionsRelations relation) {
        return userSubscriptionsRelationsRepository.save(relation);
    }

    @Override
    public Optional<UserSubscriptionsRelations> findById(UUID id) {
        return userSubscriptionsRelationsRepository.findById(id);
    }

    @Override
    public List<UserSubscriptionsRelations> findAll() {
        return userSubscriptionsRelationsRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        userSubscriptionsRelationsRepository.deleteById(id);
    }

    @Override
    public List<UserSubscriptionsRelations> findByUserId(UUID userId) {
        return userSubscriptionsRelationsRepository.findByUserId(userId);
    }

    @Override
    public List<UserSubscriptionsRelations> findBySubscriptionId(UUID subscriptionId) {
        return userSubscriptionsRelationsRepository.findBySubscriptionId(subscriptionId);
    }
}
