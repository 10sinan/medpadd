package com.vtys.medpadd.service.impl;

import com.vtys.medpadd.entity.ComicContents;
import com.vtys.medpadd.repository.ComicContentsRepository;
import com.vtys.medpadd.service.ComicContentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComicContentsServiceImpl implements ComicContentsService {

    private final ComicContentsRepository comicContentsRepository;

    @Override
    public ComicContents save(ComicContents comicContent) {
        return comicContentsRepository.save(comicContent);
    }

    @Override
    public Optional<ComicContents> findById(UUID id) {
        return comicContentsRepository.findById(id);
    }

    @Override
    public List<ComicContents> findAll() {
        return comicContentsRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        comicContentsRepository.deleteById(id);
    }

    @Override
    public List<ComicContents> findByContentId(UUID contentId) {
        return comicContentsRepository.findByContentId(contentId);
    }
}
