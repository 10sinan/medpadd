package com.vtys.medpadd.service.impl;

import com.vtys.medpadd.entity.SystemRoles;
import com.vtys.medpadd.repository.SystemRolesRepository;
import com.vtys.medpadd.service.SystemRolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SystemRolesServiceImpl implements SystemRolesService {

    private final SystemRolesRepository systemRolesRepository;

    @Override
    public SystemRoles save(SystemRoles systemRoles) {
        return systemRolesRepository.save(systemRoles);
    }

    @Override
    public Optional<SystemRoles> findById(UUID id) {
        return systemRolesRepository.findById(id);
    }

    @Override
    public List<SystemRoles> findAll() {
        return systemRolesRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        systemRolesRepository.deleteById(id);
    }

    @Override
    public Optional<SystemRoles> findByRoleName(String roleName) {
        return systemRolesRepository.findByRoleName(roleName);
    }

    @Override
    public SystemRoles findOrCreateDefaultRole() {
        return systemRolesRepository.findByRoleName("Kullanıcı")
                .orElseGet(() -> {
                    SystemRoles defaultRole = new SystemRoles();
                    defaultRole.setRoleName("Kullanıcı");
                    defaultRole.setDescription("Varsayılan kullanıcı rolü");
                    return systemRolesRepository.save(defaultRole);
                });
    }
}
