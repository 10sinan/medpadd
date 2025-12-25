package com.vtys.medpadd.repository;

import com.vtys.medpadd.entity.ContentCreatorRolesRelations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ContentCreatorRolesRelationsRepository extends JpaRepository<ContentCreatorRolesRelations, UUID> {
    List<ContentCreatorRolesRelations> findByContentCreatorsId(UUID contentCreatorId);

    List<ContentCreatorRolesRelations> findByContentCreatorsRolesId(UUID roleId);
}
