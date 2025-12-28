package com.vtys.medpadd.service.impl;

import com.vtys.medpadd.entity.ContentCreatorRoles;
import com.vtys.medpadd.repository.ContentCreatorRolesRepository;
import com.vtys.medpadd.service.ContentCreatorRolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContentCreatorRolesServiceImpl implements ContentCreatorRolesService {

    private final ContentCreatorRolesRepository contentCreatorRolesRepository;

    @Override
    public ContentCreatorRoles save(ContentCreatorRoles role) {
        return contentCreatorRolesRepository.save(role);
    }

    @Override
    public Optional<ContentCreatorRoles> findById(UUID id) {
        return contentCreatorRolesRepository.findById(id);
    }

    @Override
    public List<ContentCreatorRoles> findAll() {
        return contentCreatorRolesRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        contentCreatorRolesRepository.deleteById(id);
    }

    @Override
    public Optional<ContentCreatorRoles> findByCode(String code) {
        return contentCreatorRolesRepository.findByCode(code);
    }
}
