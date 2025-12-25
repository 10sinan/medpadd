package com.vtys.medpadd.service;

import com.vtys.medpadd.entity.Users;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsersService {
    Users save(Users user);

    Optional<Users> findById(UUID id);

    List<Users> findAll();

    void deleteById(UUID id);

    Optional<Users> findByUsername(String username);

    Optional<Users> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
