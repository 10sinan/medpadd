package com.vtys.medpadd.repository;

import com.vtys.medpadd.entity.PaintingContents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PaintingContentsRepository extends JpaRepository<PaintingContents, UUID> {
    List<PaintingContents> findByContentId(UUID contentId);
}
