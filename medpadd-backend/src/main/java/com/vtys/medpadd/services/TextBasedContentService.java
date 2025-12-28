package com.vtys.medpadd.services;

import com.vtys.medpadd.entities.TextBasedContent;
import com.vtys.medpadd.repository.TextBasedContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TextBasedContentService {

    private final TextBasedContentRepository repository;

    public List<TextBasedContent> findAll() {
        return repository.findAll();
    }

    public Optional<TextBasedContent> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    public TextBasedContent save(TextBasedContent textBasedContent) {
        return repository.save(textBasedContent);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
