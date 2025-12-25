package com.vtys.medpadd.service.impl;

import com.vtys.medpadd.entity.Media;
import com.vtys.medpadd.repository.MediaRepository;
import com.vtys.medpadd.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;

    @Override
    public Media save(Media media) {
        return mediaRepository.save(media);
    }

    @Override
    public Optional<Media> findById(UUID id) {
        return mediaRepository.findById(id);
    }

    @Override
    public List<Media> findAll() {
        return mediaRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        mediaRepository.deleteById(id);
    }

    @Override
    public List<Media> findByUserId(UUID userId) {
        return mediaRepository.findByUserId(userId);
    }
}
