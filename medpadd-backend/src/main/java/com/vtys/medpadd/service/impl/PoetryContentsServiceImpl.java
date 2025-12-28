package com.vtys.medpadd.service.impl;

import com.vtys.medpadd.entity.PoetryContents;
import com.vtys.medpadd.repository.PoetryContentsRepository;
import com.vtys.medpadd.service.PoetryContentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PoetryContentsServiceImpl implements PoetryContentsService {

    private final PoetryContentsRepository poetryContentsRepository;

    @Override
    public PoetryContents save(PoetryContents poetryContent) {
        return poetryContentsRepository.save(poetryContent);
    }

    @Override
    public Optional<PoetryContents> findById(UUID id) {
        return poetryContentsRepository.findById(id);
    }

    @Override
    public List<PoetryContents> findAll() {
        return poetryContentsRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        poetryContentsRepository.deleteById(id);
    }

    @Override
    public List<PoetryContents> findByContentId(UUID contentId) {
        return poetryContentsRepository.findByContentId(contentId);
    }
}
