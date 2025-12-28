package com.vtys.medpadd.service.impl;

import com.vtys.medpadd.entity.ContentCreatorRolesRelations;
import com.vtys.medpadd.repository.ContentCreatorRolesRelationsRepository;
import com.vtys.medpadd.service.ContentCreatorRolesRelationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContentCreatorRolesRelationsServiceImpl implements ContentCreatorRolesRelationsService {

    private final ContentCreatorRolesRelationsRepository contentCreatorRolesRelationsRepository;

    @Override
    public ContentCreatorRolesRelations save(ContentCreatorRolesRelations relation) {
        return contentCreatorRolesRelationsRepository.save(relation);
    }

    @Override
    public Optional<ContentCreatorRolesRelations> findById(UUID id) {
        return contentCreatorRolesRelationsRepository.findById(id);
    }

    @Override
    public List<ContentCreatorRolesRelations> findAll() {
        return contentCreatorRolesRelationsRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        contentCreatorRolesRelationsRepository.deleteById(id);
    }

    @Override
    public List<ContentCreatorRolesRelations> findByContentCreatorsId(UUID contentCreatorId) {
        return contentCreatorRolesRelationsRepository.findByContentCreatorsId(contentCreatorId);
    }

    @Override
    public List<ContentCreatorRolesRelations> findByContentCreatorsRolesId(UUID roleId) {
        return contentCreatorRolesRelationsRepository.findByContentCreatorsRolesId(roleId);
    }
}
