package com.vtys.medpadd.service;

import com.vtys.medpadd.entity.StoryContents;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StoryContentsService {
    StoryContents save(StoryContents story);

    Optional<StoryContents> findById(UUID id);

    List<StoryContents> findAll();

    void deleteById(UUID id);

    List<StoryContents> findByContentId(UUID contentId);
}
