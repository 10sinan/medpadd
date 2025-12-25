package com.vtys.medpadd.service;

import com.vtys.medpadd.entity.ContentCreatorVerificationsBadges;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContentCreatorVerificationsBadgesService {
    ContentCreatorVerificationsBadges save(ContentCreatorVerificationsBadges badge);

    Optional<ContentCreatorVerificationsBadges> findById(UUID id);

    List<ContentCreatorVerificationsBadges> findAll();

    void deleteById(UUID id);

    Optional<ContentCreatorVerificationsBadges> findByCode(String code);
}
