package com.vtys.medpadd.service;

import com.vtys.medpadd.entity.SystemRoles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SystemRolesService {
    SystemRoles save(SystemRoles systemRoles);

    Optional<SystemRoles> findById(UUID id);

    List<SystemRoles> findAll();

    void deleteById(UUID id);

    Optional<SystemRoles> findByRoleName(String roleName);
}
