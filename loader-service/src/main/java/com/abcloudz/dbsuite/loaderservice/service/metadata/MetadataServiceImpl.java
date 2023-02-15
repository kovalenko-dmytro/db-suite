package com.abcloudz.dbsuite.loaderservice.service.metadata;

import com.abcloudz.dbsuite.loaderservice.client.VendorServiceClient;
import com.abcloudz.dbsuite.loaderservice.common.Entity;
import com.abcloudz.dbsuite.loaderservice.common.message.Error;
import com.abcloudz.dbsuite.loaderservice.dto.connection.ConnectionResponseDTO;
import com.abcloudz.dbsuite.loaderservice.dto.metadata.MetadataResponseDTO;
import com.abcloudz.dbsuite.loaderservice.exception.EntityNotFoundException;
import com.abcloudz.dbsuite.loaderservice.exception.LoaderServiceApplicationException;
import com.abcloudz.dbsuite.loaderservice.model.category.MetadataCategory;
import com.abcloudz.dbsuite.loaderservice.model.category.MetadataCategoryType;
import com.abcloudz.dbsuite.loaderservice.model.category.VendorType;
import com.abcloudz.dbsuite.loaderservice.model.metadata.Metadata;
import com.abcloudz.dbsuite.loaderservice.repository.MetadataRepository;
import com.abcloudz.dbsuite.loaderservice.service.category.MetadataCategoryService;
import com.abcloudz.dbsuite.loaderservice.service.loader.VendorLoader;
import com.abcloudz.dbsuite.loaderservice.service.loader.provider.VendorLoaderProvider;
import com.abcloudz.dbsuite.loaderservice.util.mapper.MetadataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MetadataServiceImpl implements MetadataService {

    private final VendorServiceClient vendorServiceClient;
    private final VendorLoaderProvider vendorLoaderProvider;
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
        MetadataCategory category = metadataCategoryService
            .findModelByMetadataCategoryGuid(metadataCategoryGuid, locale);
        Metadata parent = null;
        if (!category.getType().equals(MetadataCategoryType.SERVERS)) {
            parent = getParentMetadata(connectionGuid, locale, connection, category);
        }
        VendorType vendorType = VendorType.getType(connection.getVendor().getType());
        VendorLoader vendorLoader = vendorLoaderProvider.getVendorLoader(vendorType, locale);
        Metadata metadata = vendorLoader.load(connection, category, parent, locale);
        metadata.setAddedAt(LocalDateTime.now());
        //Metadata savedMetadata = metadataRepository.save(metadata);
        return metadataMapper.clearSubChildren(metadataMapper.toMetadataResponseDTO(metadata));
    }

    private Metadata getParentMetadata(String connectionGuid, Locale locale,
                                       ConnectionResponseDTO connection, MetadataCategory category) {
        return metadataRepository
            .findByConnectionGuidAndCategory_metadataCategoryGuid(connectionGuid, category.getParent().getMetadataCategoryGuid())
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
