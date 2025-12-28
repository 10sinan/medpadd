package com.vtys.medpadd.services;

import com.vtys.medpadd.entities.ContentCreatorRoleRelation;
import com.vtys.medpadd.entities.ContentCreatorRoleRelation.ContentCreatorRoleRelationId;
import com.vtys.medpadd.repository.ContentCreatorRoleRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContentCreatorRoleRelationService {

    private final ContentCreatorRoleRelationRepository repository;

    public List<ContentCreatorRoleRelation> findAll() {
        return repository.findAll();
    }

    public Optional<ContentCreatorRoleRelation> findById(ContentCreatorRoleRelationId id) {
        return repository.findById(id);
    }

    @Transactional
    public ContentCreatorRoleRelation save(ContentCreatorRoleRelation relation) {
        return repository.save(relation);
    }

    @Transactional
    public void delete(ContentCreatorRoleRelationId id) {
        repository.deleteById(id);
    }
}
