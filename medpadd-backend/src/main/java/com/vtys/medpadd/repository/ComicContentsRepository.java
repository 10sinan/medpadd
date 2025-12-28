package com.vtys.medpadd.repository;

import com.vtys.medpadd.entity.ComicContents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ComicContentsRepository extends JpaRepository<ComicContents, UUID> {
    List<ComicContents> findByContentId(UUID contentId);
}
