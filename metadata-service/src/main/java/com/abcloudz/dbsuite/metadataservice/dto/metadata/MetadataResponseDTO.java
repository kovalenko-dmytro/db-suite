package com.abcloudz.dbsuite.metadataservice.dto.metadata;

import com.abcloudz.dbsuite.metadataservice.dto.category.BaseMetadataCategoryResponseDTO;
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
public class MetadataResponseDTO {

    private String metadataGuid;
    private String connectionGuid;
    private String type;
    private BaseMetadataCategoryResponseDTO category;
    private List<MetadataPropertyResponseDTO> properties;
    private String parent;
    private List<MetadataResponseDTO> children = new ArrayList<>();
    private LocalDateTime addedAt;
}
