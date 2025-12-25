package com.vtys.medpadd.service.impl;

import com.vtys.medpadd.entity.StoryContents;
import com.vtys.medpadd.repository.StoryContentsRepository;
import com.vtys.medpadd.service.StoryContentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StoryContentsServiceImpl implements StoryContentsService {

    private final StoryContentsRepository storyContentsRepository;

    @Override
    public StoryContents save(StoryContents story) {
        return storyContentsRepository.save(story);
    }

    @Override
    public Optional<StoryContents> findById(UUID id) {
        return storyContentsRepository.findById(id);
    }

    @Override
    public List<StoryContents> findAll() {
        return storyContentsRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        storyContentsRepository.deleteById(id);
    }

    @Override
    public List<StoryContents> findByContentId(UUID contentId) {
        return storyContentsRepository.findByContentId(contentId);
    }
}
