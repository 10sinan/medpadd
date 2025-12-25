package com.vtys.medpadd.dto;

import com.vtys.medpadd.common.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JoinMembershipDto extends BaseDto {
    private UUID id;
    private UUID contentCreatorId;
    private UUID iconId;
    private String color;
    private String name;
    private String description;
}
