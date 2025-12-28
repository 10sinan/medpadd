package com.vtys.medpadd.services;

import com.vtys.medpadd.entities.ContentCreator;
import com.vtys.medpadd.repository.ContentCreatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContentCreatorService {

    private final ContentCreatorRepository repository;

    public List<ContentCreator> findAll() {
        return repository.findAll();
    }

    public Optional<ContentCreator> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    public ContentCreator save(ContentCreator contentCreator) {
        return repository.save(contentCreator);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
