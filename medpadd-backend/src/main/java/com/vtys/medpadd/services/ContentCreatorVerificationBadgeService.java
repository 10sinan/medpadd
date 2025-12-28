package com.vtys.medpadd.services;

import com.vtys.medpadd.entities.ContentCreatorVerificationBadge;
import com.vtys.medpadd.repository.ContentCreatorVerificationBadgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContentCreatorVerificationBadgeService {

    private final ContentCreatorVerificationBadgeRepository repository;

    public List<ContentCreatorVerificationBadge> findAll() {
        return repository.findAll();
    }

    public Optional<ContentCreatorVerificationBadge> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    public ContentCreatorVerificationBadge save(ContentCreatorVerificationBadge badge) {
        return repository.save(badge);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
