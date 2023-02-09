package com.abcloudz.dbsuite.loaderservice.service;

import com.abcloudz.dbsuite.loaderservice.dto.metadata.MetadataResponseDTO;

import java.util.Locale;

public interface MetadataService {

    MetadataResponseDTO findByMetadataGuid(String metadataGuid, Locale locale);
    void delete(String metadataGuid, Locale locale);
}
