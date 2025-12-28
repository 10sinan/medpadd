package com.vtys.medpadd.services;

import com.vtys.medpadd.entities.ContentTag;
import com.vtys.medpadd.repository.ContentTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContentTagService {

    private final ContentTagRepository repository;

    public List<ContentTag> findAll() {
        return repository.findAll();
    }

    public Optional<ContentTag> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    public ContentTag save(ContentTag tag) {
        return repository.save(tag);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
