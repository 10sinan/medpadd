package com.vtys.medpadd.service.impl;

import com.vtys.medpadd.entity.Complaints;
import com.vtys.medpadd.repository.ComplaintsRepository;
import com.vtys.medpadd.service.ComplaintsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComplaintsServiceImpl implements ComplaintsService {

    private final ComplaintsRepository complaintsRepository;

    @Override
    public Complaints save(Complaints complaint) {
        return complaintsRepository.save(complaint);
    }

    @Override
    public Optional<Complaints> findById(UUID id) {
        return complaintsRepository.findById(id);
    }

    @Override
    public List<Complaints> findAll() {
        return complaintsRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        complaintsRepository.deleteById(id);
    }

    @Override
    public List<Complaints> findByTargetIdAndTargetType(UUID targetId, String targetType) {
        return complaintsRepository.findByTargetIdAndTargetType(targetId, targetType);
    }

    @Override
    public List<Complaints> findByUserId(UUID userId) {
        return complaintsRepository.findByUserId(userId);
    }
}
