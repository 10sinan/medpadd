package com.vtys.medpadd.service.impl;

import com.vtys.medpadd.entity.ContentCreatorVerificationsBadges;
import com.vtys.medpadd.repository.ContentCreatorVerificationsBadgesRepository;
import com.vtys.medpadd.service.ContentCreatorVerificationsBadgesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContentCreatorVerificationsBadgesServiceImpl implements ContentCreatorVerificationsBadgesService {

    private final ContentCreatorVerificationsBadgesRepository contentCreatorVerificationsBadgesRepository;

    @Override
    public ContentCreatorVerificationsBadges save(ContentCreatorVerificationsBadges verificationBadge) {
        return contentCreatorVerificationsBadgesRepository.save(verificationBadge);
    }

    @Override
    public Optional<ContentCreatorVerificationsBadges> findById(UUID id) {
        return contentCreatorVerificationsBadgesRepository.findById(id);
    }

    @Override
    public List<ContentCreatorVerificationsBadges> findAll() {
        return contentCreatorVerificationsBadgesRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        contentCreatorVerificationsBadgesRepository.deleteById(id);
    }

    @Override
    public Optional<ContentCreatorVerificationsBadges> findByCode(String code) {
        return contentCreatorVerificationsBadgesRepository.findByCode(code);
    }
}
