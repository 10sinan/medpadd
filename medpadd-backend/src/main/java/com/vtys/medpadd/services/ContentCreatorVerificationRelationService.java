package com.vtys.medpadd.services;

import com.vtys.medpadd.entities.ContentCreatorVerificationRelation;
import com.vtys.medpadd.entities.ContentCreatorVerificationRelation.ContentCreatorVerificationRelationId;
import com.vtys.medpadd.repository.ContentCreatorVerificationRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContentCreatorVerificationRelationService {

    private final ContentCreatorVerificationRelationRepository repository;

    public List<ContentCreatorVerificationRelation> findAll() {
        return repository.findAll();
    }

    public Optional<ContentCreatorVerificationRelation> findById(ContentCreatorVerificationRelationId id) {
        return repository.findById(id);
    }

    @Transactional
    public ContentCreatorVerificationRelation save(ContentCreatorVerificationRelation relation) {
        return repository.save(relation);
    }

    @Transactional
    public void delete(ContentCreatorVerificationRelationId id) {
        repository.deleteById(id);
    }
}
