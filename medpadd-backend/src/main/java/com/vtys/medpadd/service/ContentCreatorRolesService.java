package com.vtys.medpadd.service;

import com.vtys.medpadd.entity.ContentCreatorRoles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContentCreatorRolesService {
    ContentCreatorRoles save(ContentCreatorRoles role);

    Optional<ContentCreatorRoles> findById(UUID id);

    List<ContentCreatorRoles> findAll();

    void deleteById(UUID id);

    Optional<ContentCreatorRoles> findByCode(String code);
}
