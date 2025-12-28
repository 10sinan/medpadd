package com.vtys.medpadd.service.impl;

import com.vtys.medpadd.entity.PaintingContents;
import com.vtys.medpadd.repository.PaintingContentsRepository;
import com.vtys.medpadd.service.PaintingContentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaintingContentsServiceImpl implements PaintingContentsService {

    private final PaintingContentsRepository paintingContentsRepository;

    @Override
    public PaintingContents save(PaintingContents paintingContent) {
        return paintingContentsRepository.save(paintingContent);
    }

    @Override
    public Optional<PaintingContents> findById(UUID id) {
        return paintingContentsRepository.findById(id);
    }

    @Override
    public List<PaintingContents> findAll() {
        return paintingContentsRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        paintingContentsRepository.deleteById(id);
    }

    @Override
    public List<PaintingContents> findByContentId(UUID contentId) {
        return paintingContentsRepository.findByContentId(contentId);
    }
}
