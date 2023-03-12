package com.gmail.apachdima.dbsuite.compareservice.dto.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MetadataPropertyDTO {

    private String propertyGuid;
    private String name;
    private String value;
    private String metadataGuid;
    private LocalDateTime addedAt;
}
