package com.vtys.medpadd.repository;

import com.vtys.medpadd.entity.StoryContents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StoryContentsRepository extends JpaRepository<StoryContents, UUID> {
    List<StoryContents> findByContentId(UUID contentId);
}
