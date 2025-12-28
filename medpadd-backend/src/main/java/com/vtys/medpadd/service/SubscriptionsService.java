package com.vtys.medpadd.service;

import com.vtys.medpadd.entity.Subscriptions;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubscriptionsService {
    Subscriptions save(Subscriptions subscriptions);

    Optional<Subscriptions> findById(UUID id);

    List<Subscriptions> findAll();

    void deleteById(UUID id);

    Optional<Subscriptions> findByName(String name);
}
