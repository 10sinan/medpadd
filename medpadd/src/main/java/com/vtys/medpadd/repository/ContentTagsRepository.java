package com.vtys.medpadd.repository;

import com.vtys.medpadd.entity.ContentTags;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContentTagsRepository extends JpaRepository<ContentTags, UUID> {
}
