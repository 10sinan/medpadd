package com.vtys.medpadd.services;

import com.vtys.medpadd.entities.PaintingContent;
import com.vtys.medpadd.repository.PaintingContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaintingContentService {

    private final PaintingContentRepository repository;

    public List<PaintingContent> findAll() {
        return repository.findAll();
    }

    public Optional<PaintingContent> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    public PaintingContent save(PaintingContent paintingContent) {
        return repository.save(paintingContent);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
