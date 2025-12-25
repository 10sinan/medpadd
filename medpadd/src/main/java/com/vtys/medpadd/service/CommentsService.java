package com.vtys.medpadd.service;

import com.vtys.medpadd.entity.Comments;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentsService {
    Comments save(Comments comment);

    Optional<Comments> findById(UUID id);

    List<Comments> findAll();

    void deleteById(UUID id);

    List<Comments> findByContentId(UUID contentId);

    List<Comments> findByUserId(UUID userId);
}
