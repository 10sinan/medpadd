package com.vtys.medpadd.services;

import com.vtys.medpadd.entities.ComicContent;
import com.vtys.medpadd.repository.ComicContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ComicContentService {

    private final ComicContentRepository repository;

    public List<ComicContent> findAll() {
        return repository.findAll();
    }

    public Optional<ComicContent> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    public ComicContent save(ComicContent comicContent) {
        return repository.save(comicContent);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
