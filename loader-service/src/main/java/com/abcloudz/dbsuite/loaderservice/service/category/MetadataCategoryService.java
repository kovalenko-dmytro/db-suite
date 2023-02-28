package com.abcloudz.dbsuite.loaderservice.service.category;

import com.abcloudz.dbsuite.loaderservice.dto.category.MetadataCategoryResponseDTO;
import com.abcloudz.dbsuite.loaderservice.model.category.MetadataCategory;

import java.util.Locale;

public interface MetadataCategoryService {

    MetadataCategoryResponseDTO findRootMetadataCategory(String vendorType, Locale locale);
    MetadataCategoryResponseDTO findByMetadataCategoryGuid(String metadataCategoryGuid, Locale locale);
    MetadataCategory findModelByMetadataCategoryGuid(String metadataCategoryGuid, Locale locale);
}
