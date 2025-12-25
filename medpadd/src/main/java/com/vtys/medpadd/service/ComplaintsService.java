package com.vtys.medpadd.service;

import com.vtys.medpadd.entity.Complaints;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ComplaintsService {
    Complaints save(Complaints complaint);

    Optional<Complaints> findById(UUID id);

    List<Complaints> findAll();

    void deleteById(UUID id);

    List<Complaints> findByTargetIdAndTargetType(UUID targetId, String targetType);

    List<Complaints> findByUserId(UUID userId);
}
