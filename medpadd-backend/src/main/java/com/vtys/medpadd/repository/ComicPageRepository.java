package com.vtys.medpadd.repository;

import com.vtys.medpadd.entities.ComicPage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ComicPageRepository extends JpaRepository<ComicPage, UUID> {
}
