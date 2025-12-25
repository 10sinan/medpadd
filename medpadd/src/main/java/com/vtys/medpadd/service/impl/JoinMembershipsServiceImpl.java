package com.vtys.medpadd.service.impl;

import com.vtys.medpadd.entity.JoinMemberships;
import com.vtys.medpadd.repository.JoinMembershipsRepository;
import com.vtys.medpadd.service.JoinMembershipsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JoinMembershipsServiceImpl implements JoinMembershipsService {

    private final JoinMembershipsRepository joinMembershipsRepository;

    @Override
    public JoinMemberships save(JoinMemberships joinMembership) {
        return joinMembershipsRepository.save(joinMembership);
    }

    @Override
    public Optional<JoinMemberships> findById(UUID id) {
        return joinMembershipsRepository.findById(id);
    }

    @Override
    public List<JoinMemberships> findAll() {
        return joinMembershipsRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        joinMembershipsRepository.deleteById(id);
    }

    @Override
    public List<JoinMemberships> findByContentCreatorId(UUID contentCreatorId) {
        return joinMembershipsRepository.findByContentCreatorId(contentCreatorId);
    }
}
