package com.vtys.medpadd.services;

import com.vtys.medpadd.entities.UserBadgeRelation;
import com.vtys.medpadd.entities.UserBadgeRelation.UserBadgeRelationId;
import com.vtys.medpadd.repository.UserBadgeRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserBadgeRelationService {

    private final UserBadgeRelationRepository repository;

    public List<UserBadgeRelation> findAll() {
        return repository.findAll();
    }

    public Optional<UserBadgeRelation> findById(UserBadgeRelationId id) {
        return repository.findById(id);
    }

    @Transactional
    public UserBadgeRelation save(UserBadgeRelation relation) {
        return repository.save(relation);
    }

    @Transactional
    public void delete(UserBadgeRelationId id) {
        repository.deleteById(id);
    }
}
