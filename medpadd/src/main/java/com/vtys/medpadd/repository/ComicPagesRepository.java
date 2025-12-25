package com.vtys.medpadd.repository;

import com.vtys.medpadd.entity.ComicPages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ComicPagesRepository extends JpaRepository<ComicPages, UUID> {
    List<ComicPages> findByComicIdOrderByPageAsc(UUID comicId);
}
