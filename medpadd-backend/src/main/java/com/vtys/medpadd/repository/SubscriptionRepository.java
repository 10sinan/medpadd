package com.vtys.medpadd.repository;

import com.vtys.medpadd.entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
}
