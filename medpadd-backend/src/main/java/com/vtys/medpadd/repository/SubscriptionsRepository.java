package com.vtys.medpadd.repository;

import com.vtys.medpadd.entity.Subscriptions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SubscriptionsRepository extends JpaRepository<Subscriptions, UUID> {
    Optional<Subscriptions> findByName(String name);
}
