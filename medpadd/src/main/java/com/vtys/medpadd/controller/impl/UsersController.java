package com.vtys.medpadd.controller.impl;

import com.vtys.medpadd.entity.Users;
import com.vtys.medpadd.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PostMapping
    public Users create(@RequestBody Users user) {
        return usersService.save(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getById(@PathVariable UUID id) {
        return usersService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Users> getAll() {
        return usersService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        usersService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<Users> getByUsername(@PathVariable String username) {
        return usersService.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-email/{email}")
    public ResponseEntity<Users> getByEmail(@PathVariable String email) {
        return usersService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/exists/by-username/{username}")
    public ResponseEntity<Boolean> existsByUsername(@PathVariable String username) {
        return ResponseEntity.ok(usersService.existsByUsername(username));
    }

    @GetMapping("/exists/by-email/{email}")
    public ResponseEntity<Boolean> existsByEmail(@PathVariable String email) {
        return ResponseEntity.ok(usersService.existsByEmail(email));
    }
}
