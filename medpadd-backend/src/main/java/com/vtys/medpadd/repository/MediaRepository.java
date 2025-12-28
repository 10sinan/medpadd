package com.vtys.medpadd.repository;

import com.vtys.medpadd.entities.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface MediaRepository extends JpaRepository<Media, UUID> {
}
