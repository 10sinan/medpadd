package com.vtys.medpadd.service;

import com.vtys.medpadd.entity.ContentCreatorRolesRelations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContentCreatorRolesRelationsService {
    ContentCreatorRolesRelations save(ContentCreatorRolesRelations relation);

    Optional<ContentCreatorRolesRelations> findById(UUID id);

    List<ContentCreatorRolesRelations> findAll();

    void deleteById(UUID id);

    List<ContentCreatorRolesRelations> findByContentCreatorsId(UUID contentCreatorId);

    List<ContentCreatorRolesRelations> findByContentCreatorsRolesId(UUID roleId);
}
