package com.vtys.medpadd.services;

import com.vtys.medpadd.entities.Subscription;
import com.vtys.medpadd.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubscriptionService {

    private final SubscriptionRepository repository;

    public List<Subscription> findAll() {
        return repository.findAll();
    }

    public Optional<Subscription> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    public Subscription save(Subscription subscription) {
        return repository.save(subscription);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
