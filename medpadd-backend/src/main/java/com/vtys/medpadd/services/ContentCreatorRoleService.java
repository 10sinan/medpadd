package com.vtys.medpadd.services;

import com.vtys.medpadd.entities.ContentCreatorRole;
import com.vtys.medpadd.repository.ContentCreatorRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContentCreatorRoleService {

    private final ContentCreatorRoleRepository repository;

    public List<ContentCreatorRole> findAll() {
        return repository.findAll();
    }

    public Optional<ContentCreatorRole> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    public ContentCreatorRole save(ContentCreatorRole role) {
        return repository.save(role);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
