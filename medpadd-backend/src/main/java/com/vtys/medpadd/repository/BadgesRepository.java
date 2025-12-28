package com.vtys.medpadd.repository;

import com.vtys.medpadd.entity.Badges;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BadgesRepository extends JpaRepository<Badges, UUID> {
    Optional<Badges> findByCode(String code);
}
