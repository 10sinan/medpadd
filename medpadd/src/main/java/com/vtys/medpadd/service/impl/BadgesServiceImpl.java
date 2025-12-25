package com.vtys.medpadd.service.impl;

import com.vtys.medpadd.entity.Badges;
import com.vtys.medpadd.repository.BadgesRepository;
import com.vtys.medpadd.service.BadgesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BadgesServiceImpl implements BadgesService {

    private final BadgesRepository badgesRepository;

    @Override
    public Badges save(Badges badge) {
        return badgesRepository.save(badge);
    }

    @Override
    public Optional<Badges> findById(UUID id) {
        return badgesRepository.findById(id);
    }

    @Override
    public List<Badges> findAll() {
        return badgesRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        badgesRepository.deleteById(id);
    }

    @Override
    public Optional<Badges> findByCode(String code) {
        return badgesRepository.findByCode(code);
    }
}
