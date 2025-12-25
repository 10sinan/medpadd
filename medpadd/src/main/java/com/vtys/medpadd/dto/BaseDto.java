package com.vtys.medpadd.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class BaseDto {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
