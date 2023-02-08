package com.abcloudz.dbsuite.loaderservice.service;

import com.abcloudz.dbsuite.loaderservice.dto.category.MetadataCategoryResponseDTO;

import java.util.Locale;

public interface MetadataCategoryService {

    MetadataCategoryResponseDTO findRootMetadataCategory(String vendorType, Locale locale);
    MetadataCategoryResponseDTO findByMetadataCategoryGuid(String metadataCategoryGuid, Locale locale);
}
