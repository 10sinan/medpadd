package com.vtys.medpadd.repository;

import com.vtys.medpadd.entities.UserBoughtContentRelation;
import com.vtys.medpadd.entities.UserBoughtContentRelation.UserBoughtContentRelationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBoughtContentRelationRepository
        extends JpaRepository<UserBoughtContentRelation, UserBoughtContentRelationId> {
}
