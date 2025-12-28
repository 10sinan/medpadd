package com.vtys.medpadd.service.impl;

import com.vtys.medpadd.entity.UserBadgeRelations;
import com.vtys.medpadd.repository.UserBadgeRelationsRepository;
import com.vtys.medpadd.service.UserBadgeRelationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserBadgeRelationsServiceImpl implements UserBadgeRelationsService {

    private final UserBadgeRelationsRepository userBadgeRelationsRepository;

    @Override
    public UserBadgeRelations save(UserBadgeRelations relation) {
        return userBadgeRelationsRepository.save(relation);
    }

    @Override
    public Optional<UserBadgeRelations> findById(UUID id) {
        return userBadgeRelationsRepository.findById(id);
    }

    @Override
    public List<UserBadgeRelations> findAll() {
        return userBadgeRelationsRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        userBadgeRelationsRepository.deleteById(id);
    }

    @Override
    public List<UserBadgeRelations> findByUserId(UUID userId) {
        return userBadgeRelationsRepository.findByUserId(userId);
    }

    @Override
    public List<UserBadgeRelations> findByBadgeId(UUID badgeId) {
        return userBadgeRelationsRepository.findByBadgeId(badgeId);
    }
}
