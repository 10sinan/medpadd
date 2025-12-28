package com.vtys.medpadd.services;

import com.vtys.medpadd.entities.JoinMembership;
import com.vtys.medpadd.repository.JoinMembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JoinMembershipService {

    private final JoinMembershipRepository repository;

    public List<JoinMembership> findAll() {
        return repository.findAll();
    }

    public Optional<JoinMembership> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    public JoinMembership save(JoinMembership joinMembership) {
        return repository.save(joinMembership);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
