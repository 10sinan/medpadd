package com.vtys.medpadd.repository;

import com.vtys.medpadd.entities.PaintingContent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PaintingContentRepository extends JpaRepository<PaintingContent, UUID> {
}
