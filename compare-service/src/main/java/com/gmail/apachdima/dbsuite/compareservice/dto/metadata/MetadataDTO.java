package com.gmail.apachdima.dbsuite.compareservice.dto.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MetadataDTO {

    private String metadataGuid;
    private String connectionGuid;
    private String type;
    private MetadataCategoryDTO category;
    private List<MetadataPropertyDTO> properties;
    private String parent;
    private List<MetadataDTO> children = new ArrayList<>();
    private LocalDateTime addedAt;
}
