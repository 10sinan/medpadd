package com.vtys.medpadd.service.impl;

import com.vtys.medpadd.entity.FollowRelations;
import com.vtys.medpadd.repository.FollowRelationsRepository;
import com.vtys.medpadd.service.FollowRelationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FollowRelationsServiceImpl implements FollowRelationsService {

    private final FollowRelationsRepository followRelationsRepository;

    @Override
    public FollowRelations save(FollowRelations relation) {
        return followRelationsRepository.save(relation);
    }

    @Override
    public Optional<FollowRelations> findById(UUID id) {
        return followRelationsRepository.findById(id);
    }

    @Override
    public List<FollowRelations> findAll() {
        return followRelationsRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        followRelationsRepository.deleteById(id);
    }

    @Override
    public List<FollowRelations> findByContentCreatorsId(UUID contentCreatorId) {
        return followRelationsRepository.findByContentCreatorsId(contentCreatorId);
    }

    @Override
    public List<FollowRelations> findByComContentCreatorRolesId(UUID roleId) {
        return followRelationsRepository.findByComContentCreatorRolesId(roleId);
    }
}
