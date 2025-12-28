package com.vtys.medpadd.services;

import com.vtys.medpadd.entities.User;
import com.vtys.medpadd.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public Optional<User> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    public User save(User user) {
        return repository.save(user);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
