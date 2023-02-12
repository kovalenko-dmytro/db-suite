package com.abcloudz.dbsuite.loaderservice.service.impl;

import com.abcloudz.dbsuite.loaderservice.client.VendorServiceClient;
import com.abcloudz.dbsuite.loaderservice.common.Entity;
import com.abcloudz.dbsuite.loaderservice.common.message.Error;
import com.abcloudz.dbsuite.loaderservice.dto.category.BaseMetadataCategoryResponseDTO;
import com.abcloudz.dbsuite.loaderservice.dto.category.MetadataCategoryResponseDTO;
import com.abcloudz.dbsuite.loaderservice.dto.connection.ConnectionResponseDTO;
import com.abcloudz.dbsuite.loaderservice.dto.metadata.MetadataResponseDTO;
import com.abcloudz.dbsuite.loaderservice.exception.EntityNotFoundException;
import com.abcloudz.dbsuite.loaderservice.exception.LoaderServiceApplicationException;
import com.abcloudz.dbsuite.loaderservice.model.category.MetadataCategoryType;
import com.abcloudz.dbsuite.loaderservice.model.metadata.Metadata;
import com.abcloudz.dbsuite.loaderservice.model.metadata.MetadataType;
import com.abcloudz.dbsuite.loaderservice.repository.MetadataRepository;
import com.abcloudz.dbsuite.loaderservice.service.MetadataCategoryService;
import com.abcloudz.dbsuite.loaderservice.service.MetadataService;
import com.abcloudz.dbsuite.loaderservice.util.mapper.MetadataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MetadataServiceImpl implements MetadataService {

    private final VendorServiceClient vendorServiceClient;
    private final MetadataCategoryService metadataCategoryService;
    private final MetadataRepository metadataRepository;
    private final MessageSource messageSource;
    private final MetadataMapper metadataMapper;

    @Override
    public MetadataResponseDTO findByMetadataGuid(String metadataGuid, Locale locale) {
        Metadata metadata = getMetadataByMetadataGuid(metadataGuid, locale);
        return metadataMapper.clearSubChildren(metadataMapper.toMetadataResponseDTO(metadata));
    }

    @Override
    public MetadataResponseDTO load(String vendorGuid, String connectionGuid, String metadataCategoryGuid, Locale locale) {
        ConnectionResponseDTO connection = vendorServiceClient.findByGuid(vendorGuid, connectionGuid, locale);
        MetadataCategoryResponseDTO category = metadataCategoryService
            .findByMetadataCategoryGuid(metadataCategoryGuid, locale);
        if (!category.getType().equals(MetadataCategoryType.SERVERS.getType())) {
            getParentMetadata(connectionGuid, locale, connection, category);
        }
        return MetadataResponseDTO.builder()
            .category(BaseMetadataCategoryResponseDTO.builder()
                .metadataCategoryGuid(category.getMetadataCategoryGuid())
                .type(category.getType()).build())
            .connectionGuid(connection.getConnectionGuid())
            .type(MetadataType.SERVER.getType()).build();
    }

    private Metadata getParentMetadata(String connectionGuid, Locale locale,
                                       ConnectionResponseDTO connection, MetadataCategoryResponseDTO category) {
        return metadataRepository
            .findByConnectionGuidAndCategory_metadataCategoryGuid(connectionGuid, category.getParent())
            .orElseThrow(() ->
                new LoaderServiceApplicationException(messageSource.getMessage(
                    Error.LOADER_METADATA_PARENT_NOT_LOADED.getKey(),
                    new Object[]{connection.getConnectionName(), category.getType()}, locale)));
    }

    @Override
    public void delete(String metadataGuid, Locale locale) {
        Metadata metadata = getMetadataByMetadataGuid(metadataGuid, locale);
        metadataRepository.delete(metadata);
    }

    private Metadata getMetadataByMetadataGuid(String metadataGuid, Locale locale) {
        return metadataRepository
            .findById(metadataGuid)
            .orElseThrow(() ->
                new EntityNotFoundException(
                    messageSource.getMessage(Error.ENTITY_NOT_FOUND.getKey(), params(metadataGuid), locale)));
    }

    private Object[] params(String guid) {
        return new Object[]{Entity.METADATA.getName(), Entity.Field.GUID.getFieldName(), guid};
    }
}
