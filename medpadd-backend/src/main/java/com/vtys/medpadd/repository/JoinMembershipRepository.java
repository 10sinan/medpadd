package com.vtys.medpadd.repository;

import com.vtys.medpadd.entities.JoinMembership;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface JoinMembershipRepository extends JpaRepository<JoinMembership, UUID> {
}
