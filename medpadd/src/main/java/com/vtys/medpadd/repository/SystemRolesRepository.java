package com.vtys.medpadd.repository;

import com.vtys.medpadd.entity.SystemRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SystemRolesRepository extends JpaRepository<SystemRoles, UUID> {
    Optional<SystemRoles> findByRoleName(String roleName);
}
