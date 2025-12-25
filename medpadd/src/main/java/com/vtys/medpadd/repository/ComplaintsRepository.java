package com.vtys.medpadd.repository;

import com.vtys.medpadd.entity.Complaints;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ComplaintsRepository extends JpaRepository<Complaints, UUID> {
    List<Complaints> findByTargetIdAndTargetType(UUID targetId, String targetType);

    List<Complaints> findByUserId(UUID userId);
}
