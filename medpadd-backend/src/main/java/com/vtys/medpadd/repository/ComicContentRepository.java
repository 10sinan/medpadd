package com.vtys.medpadd.repository;

import com.vtys.medpadd.entities.ComicContent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ComicContentRepository extends JpaRepository<ComicContent, UUID> {
}
