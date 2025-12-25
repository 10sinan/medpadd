package com.vtys.medpadd.service.impl;

import com.vtys.medpadd.entity.ContentCreatorTagsRelations;
import com.vtys.medpadd.repository.ContentCreatorTagsRelationsRepository;
import com.vtys.medpadd.service.ContentCreatorTagsRelationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContentCreatorTagsRelationsServiceImpl implements ContentCreatorTagsRelationsService {

    private final ContentCreatorTagsRelationsRepository contentCreatorTagsRelationsRepository;

    @Override
    public ContentCreatorTagsRelations save(ContentCreatorTagsRelations relation) {
        return contentCreatorTagsRelationsRepository.save(relation);
    }

    @Override
    public Optional<ContentCreatorTagsRelations> findById(UUID id) {
        return contentCreatorTagsRelationsRepository.findById(id);
    }

    @Override
    public List<ContentCreatorTagsRelations> findAll() {
        return contentCreatorTagsRelationsRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        contentCreatorTagsRelationsRepository.deleteById(id);
    }

    @Override
    public List<ContentCreatorTagsRelations> findByContentCreatorId(UUID contentCreatorId) {
        return contentCreatorTagsRelationsRepository.findByContentCreatorId(contentCreatorId);
    }

    @Override
    public List<ContentCreatorTagsRelations> findByTagId(UUID tagId) {
        return contentCreatorTagsRelationsRepository.findByTagId(tagId);
    }
}
