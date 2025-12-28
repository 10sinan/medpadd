package com.vtys.medpadd.repository;

import com.vtys.medpadd.entities.ContentCreatorVerificationBadge;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ContentCreatorVerificationBadgeRepository
        extends JpaRepository<ContentCreatorVerificationBadge, UUID> {
}
