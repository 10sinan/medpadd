package com.vtys.medpadd.repository;

import com.vtys.medpadd.entity.ContentCreatorRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ContentCreatorRolesRepository extends JpaRepository<ContentCreatorRoles, UUID> {
    Optional<ContentCreatorRoles> findByCode(String code);
}
