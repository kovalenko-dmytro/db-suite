package com.abcloudz.dbsuite.loaderservice.service.metadata;

import com.abcloudz.dbsuite.loaderservice.dto.metadata.MetadataResponseDTO;

import java.util.List;
import java.util.Locale;

public interface MetadataService {

    MetadataResponseDTO findByMetadataGuid(String metadataGuid, Locale locale);
    List<MetadataResponseDTO> load(String vendorGuid, String connectionGuid, String metadataCategoryGuid, Locale locale);
    void delete(String metadataGuid, Locale locale);
}
