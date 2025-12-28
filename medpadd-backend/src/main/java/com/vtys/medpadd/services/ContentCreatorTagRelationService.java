package com.vtys.medpadd.services;

import com.vtys.medpadd.entities.ContentCreatorTagRelation;
import com.vtys.medpadd.entities.ContentCreatorTagRelation.ContentCreatorTagRelationId;
import com.vtys.medpadd.repository.ContentCreatorTagRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContentCreatorTagRelationService {

    private final ContentCreatorTagRelationRepository repository;

    public List<ContentCreatorTagRelation> findAll() {
        return repository.findAll();
    }

    public Optional<ContentCreatorTagRelation> findById(ContentCreatorTagRelationId id) {
        return repository.findById(id);
    }

    @Transactional
    public ContentCreatorTagRelation save(ContentCreatorTagRelation relation) {
        return repository.save(relation);
    }

    @Transactional
    public void delete(ContentCreatorTagRelationId id) {
        repository.deleteById(id);
    }
}
