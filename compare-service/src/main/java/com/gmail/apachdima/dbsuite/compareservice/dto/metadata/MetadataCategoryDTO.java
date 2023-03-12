package com.gmail.apachdima.dbsuite.compareservice.dto.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MetadataCategoryDTO {

    private String metadataCategoryGuid;
    private String type;
    private List<MetadataCategoryDTO> subCategories;
}
