package com.vtys.medpadd.repository;

import com.vtys.medpadd.entity.JoinMemberships;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JoinMembershipsRepository extends JpaRepository<JoinMemberships, UUID> {
    List<JoinMemberships> findByContentCreatorId(UUID contentCreatorId);
}
