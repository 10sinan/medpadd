package com.vtys.medpadd.repository;

import com.vtys.medpadd.entity.ContentCreatorVerificationsBadges;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ContentCreatorVerificationsBadgesRepository
        extends JpaRepository<ContentCreatorVerificationsBadges, UUID> {
    Optional<ContentCreatorVerificationsBadges> findByCode(String code);
}
