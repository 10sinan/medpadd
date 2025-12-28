package com.vtys.medpadd.services;

import com.vtys.medpadd.entities.UserJoinMembershipRelation;
import com.vtys.medpadd.entities.UserJoinMembershipRelation.UserJoinMembershipRelationId;
import com.vtys.medpadd.repository.UserJoinMembershipRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserJoinMembershipRelationService {

    private final UserJoinMembershipRelationRepository repository;

    public List<UserJoinMembershipRelation> findAll() {
        return repository.findAll();
    }

    public Optional<UserJoinMembershipRelation> findById(UserJoinMembershipRelationId id) {
        return repository.findById(id);
    }

    @Transactional
    public UserJoinMembershipRelation save(UserJoinMembershipRelation relation) {
        return repository.save(relation);
    }

    @Transactional
    public void delete(UserJoinMembershipRelationId id) {
        repository.deleteById(id);
    }
}
