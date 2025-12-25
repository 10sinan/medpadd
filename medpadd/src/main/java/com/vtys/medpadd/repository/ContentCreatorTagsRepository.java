package com.vtys.medpadd.repository;

import com.vtys.medpadd.entity.ContentCreatorTags;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ContentCreatorTagsRepository extends JpaRepository<ContentCreatorTags, UUID> {
    Optional<ContentCreatorTags> findByName(String name);
}
