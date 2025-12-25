package com.vtys.medpadd.service.impl;

import com.vtys.medpadd.entity.ComicPages;
import com.vtys.medpadd.repository.ComicPagesRepository;
import com.vtys.medpadd.service.ComicPagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComicPagesServiceImpl implements ComicPagesService {

    private final ComicPagesRepository comicPagesRepository;

    @Override
    public ComicPages save(ComicPages comicPage) {
        return comicPagesRepository.save(comicPage);
    }

    @Override
    public Optional<ComicPages> findById(UUID id) {
        return comicPagesRepository.findById(id);
    }

    @Override
    public List<ComicPages> findAll() {
        return comicPagesRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        comicPagesRepository.deleteById(id);
    }

    @Override
    public List<ComicPages> findByComicIdOrderByPageAsc(UUID comicId) {
        return comicPagesRepository.findByComicIdOrderByPageAsc(comicId);
    }
}
