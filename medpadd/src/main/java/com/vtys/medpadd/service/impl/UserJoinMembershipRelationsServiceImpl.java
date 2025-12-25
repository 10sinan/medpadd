package com.vtys.medpadd.service.impl;

import com.vtys.medpadd.entity.UserJoinMembershipRelations;
import com.vtys.medpadd.repository.UserJoinMembershipRelationsRepository;
import com.vtys.medpadd.service.UserJoinMembershipRelationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserJoinMembershipRelationsServiceImpl implements UserJoinMembershipRelationsService {

    private final UserJoinMembershipRelationsRepository userJoinMembershipRelationsRepository;

    @Override
    public UserJoinMembershipRelations save(UserJoinMembershipRelations relation) {
        return userJoinMembershipRelationsRepository.save(relation);
    }

    @Override
    public Optional<UserJoinMembershipRelations> findById(UUID id) {
        return userJoinMembershipRelationsRepository.findById(id);
    }

    @Override
    public List<UserJoinMembershipRelations> findAll() {
        return userJoinMembershipRelationsRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        userJoinMembershipRelationsRepository.deleteById(id);
    }

    @Override
    public List<UserJoinMembershipRelations> findByUserId(UUID userId) {
        return userJoinMembershipRelationsRepository.findByUserId(userId);
    }

    @Override
    public List<UserJoinMembershipRelations> findByJoinMembershipId(UUID joinMembershipId) {
        return userJoinMembershipRelationsRepository.findByJoinMembershipId(joinMembershipId);
    }
}
