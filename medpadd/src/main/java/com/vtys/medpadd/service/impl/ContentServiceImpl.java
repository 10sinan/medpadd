package com.vtys.medpadd.service.impl;

import com.vtys.medpadd.entity.Content;
import com.vtys.medpadd.repository.ContentRepository;
import com.vtys.medpadd.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;

    @Override
    public Content save(Content content) {
        return contentRepository.save(content);
    }

    @Override
    public Optional<Content> findById(UUID id) {
        return contentRepository.findById(id);
    }

    @Override
    public List<Content> findAll() {
        return contentRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        contentRepository.deleteById(id);
    }

    @Override
    public List<Content> findByContentCreatorId(UUID contentCreatorId) {
        return contentRepository.findByContentCreatorId(contentCreatorId);
    }
}
