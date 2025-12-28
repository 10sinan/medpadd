package com.vtys.medpadd.repository;

import com.vtys.medpadd.entities.ContentTag;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ContentTagRepository extends JpaRepository<ContentTag, UUID> {
}
