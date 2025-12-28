package com.vtys.medpadd.service.impl;

import com.vtys.medpadd.entity.ContentCreatorTags;
import com.vtys.medpadd.repository.ContentCreatorTagsRepository;
import com.vtys.medpadd.service.ContentCreatorTagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContentCreatorTagsServiceImpl implements ContentCreatorTagsService {

    private final ContentCreatorTagsRepository contentCreatorTagsRepository;

    @Override
    public ContentCreatorTags save(ContentCreatorTags tag) {
        return contentCreatorTagsRepository.save(tag);
    }

    @Override
    public Optional<ContentCreatorTags> findById(UUID id) {
        return contentCreatorTagsRepository.findById(id);
    }

    @Override
    public List<ContentCreatorTags> findAll() {
        return contentCreatorTagsRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        contentCreatorTagsRepository.deleteById(id);
    }

    @Override
    public Optional<ContentCreatorTags> findByName(String name) {
        return contentCreatorTagsRepository.findByName(name);
    }
}
