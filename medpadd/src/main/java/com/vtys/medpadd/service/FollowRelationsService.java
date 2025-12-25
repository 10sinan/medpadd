package com.vtys.medpadd.service;

import com.vtys.medpadd.entity.FollowRelations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FollowRelationsService {
    FollowRelations save(FollowRelations relation);

    Optional<FollowRelations> findById(UUID id);

    List<FollowRelations> findAll();

    void deleteById(UUID id);

    List<FollowRelations> findByContentCreatorsId(UUID contentCreatorId);

    List<FollowRelations> findByComContentCreatorRolesId(UUID roleId);
}
