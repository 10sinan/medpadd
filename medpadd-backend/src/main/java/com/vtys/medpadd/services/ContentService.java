package com.vtys.medpadd.services;

import com.vtys.medpadd.entities.Content;
import com.vtys.medpadd.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContentService {

    private final ContentRepository repository;

    public List<Content> findAll() {
        return repository.findAll();
    }

    public Optional<Content> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    public Content save(Content content) {
        return repository.save(content);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
