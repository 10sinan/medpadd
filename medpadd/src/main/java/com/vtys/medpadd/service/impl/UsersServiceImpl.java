package com.vtys.medpadd.service.impl;

import com.vtys.medpadd.entity.Users;
import com.vtys.medpadd.repository.UsersRepository;
import com.vtys.medpadd.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Override
    public Users save(Users user) {
        return usersRepository.save(user);
    }

    @Override
    public Optional<Users> findById(UUID id) {
        return usersRepository.findById(id);
    }

    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        usersRepository.deleteById(id);
    }

    @Override
    public Optional<Users> findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    @Override
    public Optional<Users> findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return usersRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return usersRepository.existsByEmail(email);
    }
}
