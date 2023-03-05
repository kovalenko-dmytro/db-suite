package com.abcloudz.dbsuite.metadataservice.dto.metadata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoadMetadataRequestDTO {

    @NotBlank
    String vendorGuid;
    @NotBlank
    String connectionGuid;
    @NotBlank
    String metadataCategoryGuid;
    String parentMetadataGuid;
}
