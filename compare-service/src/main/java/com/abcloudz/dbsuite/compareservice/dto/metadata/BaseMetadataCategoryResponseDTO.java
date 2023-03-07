package com.abcloudz.dbsuite.compareservice.dto.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BaseMetadataCategoryResponseDTO {

    private String metadataCategoryGuid;
    private String type;
    private List<BaseMetadataCategoryResponseDTO> subCategories;
}
