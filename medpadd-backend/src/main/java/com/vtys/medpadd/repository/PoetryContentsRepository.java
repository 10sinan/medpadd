package com.vtys.medpadd.repository;

import com.vtys.medpadd.entity.PoetryContents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PoetryContentsRepository extends JpaRepository<PoetryContents, UUID> {
    List<PoetryContents> findByContentId(UUID contentId);
}
