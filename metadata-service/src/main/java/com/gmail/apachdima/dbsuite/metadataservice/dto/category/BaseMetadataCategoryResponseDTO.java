package com.gmail.apachdima.dbsuite.metadataservice.dto.category;

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
