package com.vtys.medpadd.services;

import com.vtys.medpadd.entities.ComicPage;
import com.vtys.medpadd.repository.ComicPageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ComicPageService {

    private final ComicPageRepository repository;

    public List<ComicPage> findAll() {
        return repository.findAll();
    }

    public Optional<ComicPage> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    public ComicPage save(ComicPage comicPage) {
        return repository.save(comicPage);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
