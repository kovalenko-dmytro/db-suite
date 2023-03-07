package com.gmail.apachdima.dbsuite.metadataservice.service.metadata;

import com.gmail.apachdima.dbsuite.metadataservice.dto.metadata.LoadMetadataRequestDTO;
import com.gmail.apachdima.dbsuite.metadataservice.dto.metadata.MetadataResponseDTO;

import java.util.List;
import java.util.Locale;

public interface MetadataService {

    MetadataResponseDTO findByMetadataGuid(String metadataGuid, Locale locale);
    List<MetadataResponseDTO> load(LoadMetadataRequestDTO request, boolean full, Locale locale);
    void delete(String metadataGuid, Locale locale);
}
