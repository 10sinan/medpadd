package com.vtys.medpadd.dto;

import com.vtys.medpadd.common.dto.BaseDto;
import com.vtys.medpadd.common.dto.ContentItem;
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
public class PaintingContentDto extends BaseDto implements ContentItem {
    private UUID id;
    private UUID contentId;
    private UUID imageId;
    private String style;
    private String description;
}
