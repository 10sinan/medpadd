package com.vtys.medpadd.service.impl;

import com.vtys.medpadd.entity.ContentCreators;
import com.vtys.medpadd.repository.ContentCreatorsRepository;
import com.vtys.medpadd.service.ContentCreatorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContentCreatorsServiceImpl implements ContentCreatorsService {

    private final ContentCreatorsRepository contentCreatorsRepository;

    @Override
    public ContentCreators save(ContentCreators contentCreator) {
        return contentCreatorsRepository.save(contentCreator);
    }

    @Override
    public Optional<ContentCreators> findById(UUID id) {
        return contentCreatorsRepository.findById(id);
    }

    @Override
    public List<ContentCreators> findAll() {
        return contentCreatorsRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        contentCreatorsRepository.deleteById(id);
    }

    @Override
    public Optional<ContentCreators> findByUserId(UUID userId) {
        return contentCreatorsRepository.findByUserId(userId);
    }
}
