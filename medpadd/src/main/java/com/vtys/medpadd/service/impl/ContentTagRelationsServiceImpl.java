package com.vtys.medpadd.service.impl;

import com.vtys.medpadd.entity.ContentTagRelations;
import com.vtys.medpadd.repository.ContentTagRelationsRepository;
import com.vtys.medpadd.service.ContentTagRelationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContentTagRelationsServiceImpl implements ContentTagRelationsService {

    private final ContentTagRelationsRepository contentTagRelationsRepository;

    @Override
    public ContentTagRelations save(ContentTagRelations relation) {
        return contentTagRelationsRepository.save(relation);
    }

    @Override
    public Optional<ContentTagRelations> findById(UUID id) {
        return contentTagRelationsRepository.findById(id);
    }

    @Override
    public List<ContentTagRelations> findAll() {
        return contentTagRelationsRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        contentTagRelationsRepository.deleteById(id);
    }

    @Override
    public List<ContentTagRelations> findByContentId(UUID contentId) {
        return contentTagRelationsRepository.findByContentId(contentId);
    }

    @Override
    public List<ContentTagRelations> findByTagId(UUID tagId) {
        return contentTagRelationsRepository.findByTagId(tagId);
    }
}
