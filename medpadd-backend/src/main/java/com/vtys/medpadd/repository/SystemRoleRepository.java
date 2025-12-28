package com.vtys.medpadd.repository;

import com.vtys.medpadd.entities.SystemRole;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SystemRoleRepository extends JpaRepository<SystemRole, UUID> {
}
