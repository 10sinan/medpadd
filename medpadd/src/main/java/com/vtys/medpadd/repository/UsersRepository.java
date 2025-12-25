package com.vtys.medpadd.repository;

import com.vtys.medpadd.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<Users, UUID> {
    Optional<Users> findByUsername(String username);

    Optional<Users> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
