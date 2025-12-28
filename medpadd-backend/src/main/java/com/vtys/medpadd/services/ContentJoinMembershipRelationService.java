package com.vtys.medpadd.services;

import com.vtys.medpadd.entities.ContentJoinMembershipRelation;
import com.vtys.medpadd.entities.ContentJoinMembershipRelation.ContentJoinMembershipRelationId;
import com.vtys.medpadd.repository.ContentJoinMembershipRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContentJoinMembershipRelationService {

    private final ContentJoinMembershipRelationRepository repository;

    public List<ContentJoinMembershipRelation> findAll() {
        return repository.findAll();
    }

    public Optional<ContentJoinMembershipRelation> findById(ContentJoinMembershipRelationId id) {
        return repository.findById(id);
    }

    @Transactional
    public ContentJoinMembershipRelation save(ContentJoinMembershipRelation relation) {
        return repository.save(relation);
    }

    @Transactional
    public void delete(ContentJoinMembershipRelationId id) {
        repository.deleteById(id);
    }
}
