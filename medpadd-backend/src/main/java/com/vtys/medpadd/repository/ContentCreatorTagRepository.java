package com.vtys.medpadd.repository;

import com.vtys.medpadd.entities.ContentCreatorTag;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ContentCreatorTagRepository extends JpaRepository<ContentCreatorTag, UUID> {
}
