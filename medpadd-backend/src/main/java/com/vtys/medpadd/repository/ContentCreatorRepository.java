package com.vtys.medpadd.repository;

import com.vtys.medpadd.entities.ContentCreator;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ContentCreatorRepository extends JpaRepository<ContentCreator, UUID> {
}
