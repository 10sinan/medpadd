package com.vtys.medpadd.services;

import com.vtys.medpadd.entities.UserBoughtContentRelation;
import com.vtys.medpadd.entities.UserBoughtContentRelation.UserBoughtContentRelationId;
import com.vtys.medpadd.repository.UserBoughtContentRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserBoughtContentRelationService {

    private final UserBoughtContentRelationRepository repository;

    public List<UserBoughtContentRelation> findAll() {
        return repository.findAll();
    }

    public Optional<UserBoughtContentRelation> findById(UserBoughtContentRelationId id) {
        return repository.findById(id);
    }

    @Transactional
    public UserBoughtContentRelation save(UserBoughtContentRelation relation) {
        return repository.save(relation);
    }

    @Transactional
    public void delete(UserBoughtContentRelationId id) {
        repository.deleteById(id);
    }
}
