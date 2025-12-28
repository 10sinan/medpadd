package com.vtys.medpadd.services;

import com.vtys.medpadd.entities.SystemRole;
import com.vtys.medpadd.repository.SystemRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SystemRoleService {

    private final SystemRoleRepository repository;

    public List<SystemRole> findAll() {
        return repository.findAll();
    }

    public Optional<SystemRole> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    public SystemRole save(SystemRole role) {
        return repository.save(role);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
