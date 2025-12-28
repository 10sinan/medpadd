package com.vtys.medpadd.services;

import com.vtys.medpadd.entities.Media;
import com.vtys.medpadd.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MediaService {

    private final MediaRepository repository;

    public List<Media> findAll() {
        return repository.findAll();
    }

    public Optional<Media> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    public Media save(Media media) {
        return repository.save(media);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
