package com.vtys.medpadd.service.impl;

import com.vtys.medpadd.entity.ContentTags;
import com.vtys.medpadd.repository.ContentTagsRepository;
import com.vtys.medpadd.service.ContentTagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContentTagsServiceImpl implements ContentTagsService {

    private final ContentTagsRepository contentTagsRepository;

    @Override
    public ContentTags save(ContentTags tag) {
        return contentTagsRepository.save(tag);
    }

    @Override
    public Optional<ContentTags> findById(UUID id) {
        return contentTagsRepository.findById(id);
    }

    @Override
    public List<ContentTags> findAll() {
        return contentTagsRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        contentTagsRepository.deleteById(id);
    }
}
