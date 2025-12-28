package com.vtys.medpadd.controller.impl;

import com.vtys.medpadd.dto.SystemRoleDto;
import com.vtys.medpadd.dto.UserDto;
import com.vtys.medpadd.entity.Users;
import com.vtys.medpadd.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PostMapping
    public UserDto create(@RequestBody Users user) {
        Users savedUser = usersService.save(user);
        return toDto(savedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable UUID id) {
        return usersService.findById(id)
                .map(this::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<UserDto> getAll() {
        return usersService.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        usersService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<UserDto> getByUsername(@PathVariable String username) {
        return usersService.findByUsername(username)
                .map(this::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-email/{email}")
    public ResponseEntity<UserDto> getByEmail(@PathVariable String email) {
        return usersService.findByEmail(email)
                .map(this::toDto)
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

    private UserDto toDto(Users user) {
        SystemRoleDto roleDto = null;
        if (user.getSystemRoles() != null) {
            roleDto = SystemRoleDto.builder()
                    .id(user.getSystemRoles().getId())
                    .roleName(user.getSystemRoles().getRoleName())
                    .description(user.getSystemRoles().getDescription())
                    .build();
            roleDto.setCreatedAt(user.getSystemRoles().getCreatedAt());
            roleDto.setUpdatedAt(user.getSystemRoles().getUpdatedAt());
        }

        UserDto dto = UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .roleId(user.getSystemRoles() != null ? user.getSystemRoles().getId() : null)
                .systemRoles(roleDto)
                .birthday(user.getBirthday())
                .profilePicId(user.getProfilePic() != null ? user.getProfilePic().getId() : null)
                .build();
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        return dto;
    }
}
