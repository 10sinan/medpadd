package com.vtys.medpadd.services;

import com.vtys.medpadd.entities.Complaint;
import com.vtys.medpadd.repository.ComplaintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ComplaintService {

    private final ComplaintRepository repository;

    public List<Complaint> findAll() {
        return repository.findAll();
    }

    public Optional<Complaint> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    public Complaint save(Complaint complaint) {
        return repository.save(complaint);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
