package com.vtys.medpadd.service;

import com.vtys.medpadd.entity.Badges;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BadgesService {
    Badges save(Badges badge);

    Optional<Badges> findById(UUID id);

    List<Badges> findAll();

    void deleteById(UUID id);

    Optional<Badges> findByCode(String code);
}
