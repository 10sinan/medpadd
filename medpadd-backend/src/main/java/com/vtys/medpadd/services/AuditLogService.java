package com.vtys.medpadd.services;

import com.vtys.medpadd.entities.AuditLog;
import com.vtys.medpadd.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuditLogService {

    private final AuditLogRepository repository;

    public List<AuditLog> findAll() {
        return repository.findAll();
    }

    public Optional<AuditLog> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    public AuditLog save(AuditLog auditLog) {
        return repository.save(auditLog);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
