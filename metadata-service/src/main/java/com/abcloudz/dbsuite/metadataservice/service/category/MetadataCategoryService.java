package com.abcloudz.dbsuite.metadataservice.service.category;

import com.abcloudz.dbsuite.metadataservice.dto.category.MetadataCategoryResponseDTO;
import com.abcloudz.dbsuite.metadataservice.model.category.MetadataCategory;

import java.util.Locale;

public interface MetadataCategoryService {

    MetadataCategoryResponseDTO findRootMetadataCategory(String vendorType, Locale locale);
    MetadataCategoryResponseDTO findByMetadataCategoryGuid(String metadataCategoryGuid, Locale locale);
    MetadataCategory findModelByMetadataCategoryGuid(String metadataCategoryGuid, Locale locale);
}
