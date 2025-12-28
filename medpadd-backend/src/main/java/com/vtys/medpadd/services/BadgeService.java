package com.vtys.medpadd.services;

import com.vtys.medpadd.entities.Badge;
import com.vtys.medpadd.repository.BadgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BadgeService {

    private final BadgeRepository repository;

    public List<Badge> findAll() {
        return repository.findAll();
    }

    public Optional<Badge> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    public Badge save(Badge badge) {
        return repository.save(badge);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
