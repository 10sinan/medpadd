package com.vtys.medpadd.repository;

import com.vtys.medpadd.entities.TextBasedContent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TextBasedContentRepository extends JpaRepository<TextBasedContent, UUID> {
}
