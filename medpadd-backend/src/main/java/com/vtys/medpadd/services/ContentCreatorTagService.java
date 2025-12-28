package com.vtys.medpadd.services;

import com.vtys.medpadd.entities.ContentCreatorTag;
import com.vtys.medpadd.repository.ContentCreatorTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContentCreatorTagService {

    private final ContentCreatorTagRepository repository;

    public List<ContentCreatorTag> findAll() {
        return repository.findAll();
    }

    public Optional<ContentCreatorTag> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    public ContentCreatorTag save(ContentCreatorTag tag) {
        return repository.save(tag);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
