package com.vtys.medpadd.repository;

import com.vtys.medpadd.entities.ContentCreatorRole;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ContentCreatorRoleRepository extends JpaRepository<ContentCreatorRole, UUID> {
}
