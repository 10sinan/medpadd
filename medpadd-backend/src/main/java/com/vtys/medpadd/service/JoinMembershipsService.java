package com.vtys.medpadd.service;

import com.vtys.medpadd.entity.JoinMemberships;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JoinMembershipsService {
    JoinMemberships save(JoinMemberships joinMembership);

    Optional<JoinMemberships> findById(UUID id);

    List<JoinMemberships> findAll();

    void deleteById(UUID id);

    List<JoinMemberships> findByContentCreatorId(UUID contentCreatorId);
}
