package com.vtys.medpadd.service.impl;

import com.vtys.medpadd.entity.Comments;
import com.vtys.medpadd.repository.CommentsRepository;
import com.vtys.medpadd.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final CommentsRepository commentsRepository;

    @Override
    public Comments save(Comments comment) {
        return commentsRepository.save(comment);
    }

    @Override
    public Optional<Comments> findById(UUID id) {
        return commentsRepository.findById(id);
    }

    @Override
    public List<Comments> findAll() {
        return commentsRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        commentsRepository.deleteById(id);
    }

    @Override
    public List<Comments> findByContentId(UUID contentId) {
        return commentsRepository.findByContentId(contentId);
    }

    @Override
    public List<Comments> findByUserId(UUID userId) {
        return commentsRepository.findByUserId(userId);
    }
}
