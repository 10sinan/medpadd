package com.vtys.medpadd.services;

import com.vtys.medpadd.entities.FollowRelation;
import com.vtys.medpadd.entities.FollowRelation.FollowRelationId;
import com.vtys.medpadd.repository.FollowRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FollowRelationService {

    private final FollowRelationRepository repository;

    public List<FollowRelation> findAll() {
        return repository.findAll();
    }

    public Optional<FollowRelation> findById(FollowRelationId id) {
        return repository.findById(id);
    }

    @Transactional
    public FollowRelation save(FollowRelation followRelation) {
        return repository.save(followRelation);
    }

    @Transactional
    public void delete(FollowRelationId id) {
        repository.deleteById(id);
    }
}
