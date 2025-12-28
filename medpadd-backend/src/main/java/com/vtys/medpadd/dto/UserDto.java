package com.vtys.medpadd.dto;

import com.vtys.medpadd.common.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private UUID roleId;
    private SystemRoleDto systemRoles;
    private LocalDate birthday;
    private UUID profilePicId;
}
