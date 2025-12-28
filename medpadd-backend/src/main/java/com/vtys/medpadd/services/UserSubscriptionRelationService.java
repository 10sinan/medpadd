package com.vtys.medpadd.services;

import com.vtys.medpadd.entities.UserSubscriptionRelation;
import com.vtys.medpadd.entities.UserSubscriptionRelation.UserSubscriptionRelationId;
import com.vtys.medpadd.repository.UserSubscriptionRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserSubscriptionRelationService {

    private final UserSubscriptionRelationRepository repository;

    public List<UserSubscriptionRelation> findAll() {
        return repository.findAll();
    }

    public Optional<UserSubscriptionRelation> findById(UserSubscriptionRelationId id) {
        return repository.findById(id);
    }

    @Transactional
    public UserSubscriptionRelation save(UserSubscriptionRelation relation) {
        return repository.save(relation);
    }

    @Transactional
    public void delete(UserSubscriptionRelationId id) {
        repository.deleteById(id);
    }
}
