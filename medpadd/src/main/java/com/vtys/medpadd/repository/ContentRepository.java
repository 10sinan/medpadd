package com.vtys.medpadd.repository;

import com.vtys.medpadd.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ContentRepository extends JpaRepository<Content, UUID> {
    List<Content> findByContentCreatorId(UUID contentCreatorId);
}
