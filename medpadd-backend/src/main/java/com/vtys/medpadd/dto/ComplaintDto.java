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
public class ComplaintDto extends BaseDto {
    private UUID id;
    private UUID userId;
    private UUID mediaId;
    private UUID targetId;
    private String targetType;
    private String title;
    private String complaint;
}
