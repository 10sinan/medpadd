package com.vtys.medpadd.service.impl;

import com.vtys.medpadd.entity.Subscriptions;
import com.vtys.medpadd.repository.SubscriptionsRepository;
import com.vtys.medpadd.service.SubscriptionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionsServiceImpl implements SubscriptionsService {

    private final SubscriptionsRepository subscriptionsRepository;

    @Override
    public Subscriptions save(Subscriptions subscriptions) {
        return subscriptionsRepository.save(subscriptions);
    }

    @Override
    public Optional<Subscriptions> findById(UUID id) {
        return subscriptionsRepository.findById(id);
    }

    @Override
    public List<Subscriptions> findAll() {
        return subscriptionsRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        subscriptionsRepository.deleteById(id);
    }

    @Override
    public Optional<Subscriptions> findByName(String name) {
        return subscriptionsRepository.findByName(name);
    }
}
