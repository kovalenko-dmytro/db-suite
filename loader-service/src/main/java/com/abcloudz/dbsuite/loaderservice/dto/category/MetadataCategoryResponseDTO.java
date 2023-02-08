package com.abcloudz.dbsuite.loaderservice.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MetadataCategoryResponseDTO {

    private String metadataCategoryGuid;
    private String type;
    private boolean root;
    private String vendorGuid;
    private String parent;
    private String versionFrom;
    private LocalDateTime addedAt;
    private List<MetadataCategoryResponseDTO> subCategories;
}
