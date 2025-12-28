package com.vtys.medpadd.services;

import com.vtys.medpadd.entities.ContentTagRelation;
import com.vtys.medpadd.entities.ContentTagRelation.ContentTagRelationId;
import com.vtys.medpadd.repository.ContentTagRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContentTagRelationService {

    private final ContentTagRelationRepository repository;

    public List<ContentTagRelation> findAll() {
        return repository.findAll();
    }

    public Optional<ContentTagRelation> findById(ContentTagRelationId id) {
        return repository.findById(id);
    }

    @Transactional
    public ContentTagRelation save(ContentTagRelation relation) {
        return repository.save(relation);
    }

    @Transactional
    public void delete(ContentTagRelationId id) {
        repository.deleteById(id);
    }
}
