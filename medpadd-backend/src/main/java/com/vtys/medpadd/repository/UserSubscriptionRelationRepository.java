package com.vtys.medpadd.repository;

import com.vtys.medpadd.entities.UserSubscriptionRelation;
import com.vtys.medpadd.entities.UserSubscriptionRelation.UserSubscriptionRelationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSubscriptionRelationRepository
        extends JpaRepository<UserSubscriptionRelation, UserSubscriptionRelationId> {
}
