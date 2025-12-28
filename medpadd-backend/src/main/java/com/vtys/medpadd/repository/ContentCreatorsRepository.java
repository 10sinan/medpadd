package com.vtys.medpadd.repository;

import com.vtys.medpadd.entity.ContentCreators;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ContentCreatorsRepository extends JpaRepository<ContentCreators, UUID> {
    Optional<ContentCreators> findByUserId(UUID userId);
}
