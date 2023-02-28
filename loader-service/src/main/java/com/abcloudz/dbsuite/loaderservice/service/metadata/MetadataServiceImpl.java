package com.abcloudz.dbsuite.loaderservice.service.metadata;

import com.abcloudz.dbsuite.loaderservice.client.VendorServiceClient;
import com.abcloudz.dbsuite.loaderservice.common.Entity;
import com.abcloudz.dbsuite.loaderservice.common.message.Error;
import com.abcloudz.dbsuite.loaderservice.dto.connection.ConnectionResponseDTO;
import com.abcloudz.dbsuite.loaderservice.dto.loader.LoadContext;
import com.abcloudz.dbsuite.loaderservice.dto.metadata.LoadMetadataRequestDTO;
import com.abcloudz.dbsuite.loaderservice.dto.metadata.MetadataResponseDTO;
import com.abcloudz.dbsuite.loaderservice.exception.EntityNotFoundException;
import com.abcloudz.dbsuite.loaderservice.exception.LoaderServiceApplicationException;
import com.abcloudz.dbsuite.loaderservice.model.category.MetadataCategory;
import com.abcloudz.dbsuite.loaderservice.model.category.MetadataCategoryType;
import com.abcloudz.dbsuite.loaderservice.model.metadata.Metadata;
import com.abcloudz.dbsuite.loaderservice.repository.MetadataRepository;
import com.abcloudz.dbsuite.loaderservice.service.category.MetadataCategoryService;
import com.abcloudz.dbsuite.loaderservice.service.loader.ILoader;
import com.abcloudz.dbsuite.loaderservice.util.mapper.MetadataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MetadataServiceImpl implements MetadataService {

    private final VendorServiceClient vendorServiceClient;
    private final MetadataCategoryService metadataCategoryService;
    private final ILoader loader;
    private final MetadataRepository metadataRepository;
    private final MessageSource messageSource;
    private final MetadataMapper metadataMapper;

    @Override
    public MetadataResponseDTO findByMetadataGuid(String metadataGuid, Locale locale) {
        Metadata metadata = getMetadataByMetadataGuid(metadataGuid, locale);
        return metadataMapper.toMetadataResponseDTO(metadata);
    }

    @Override
    public List<MetadataResponseDTO> load(LoadMetadataRequestDTO request, Locale locale) {
        List<Metadata> existingMetadata = metadataRepository
            .findByConnectionGuidAndCategory_metadataCategoryGuidAndParent_metadataGuid(
                request.getConnectionGuid(),
                request.getMetadataCategoryGuid(),
                request.getParentMetadataGuid());
        if (!existingMetadata.isEmpty()) {
            return existingMetadata.stream().map(metadataMapper::toMetadataResponseDTO).collect(Collectors.toList());
        }
        ConnectionResponseDTO connection = vendorServiceClient
            .findByGuid(request.getVendorGuid(), request.getConnectionGuid(), locale);
        MetadataCategory category = metadataCategoryService
            .findModelByMetadataCategoryGuid(request.getMetadataCategoryGuid(), locale);
        Metadata parent = category.getType().equals(MetadataCategoryType.SERVERS)
            ? null
            : getParentMetadata(request.getParentMetadataGuid(), connection.getConnectionName(), category.getType().getType(), locale);

        LoadContext context = LoadContext.builder().connection(connection).category(category).parent(parent).build();
        List<Metadata> metadata = loader.load(context, locale);

        return metadataRepository.saveAll(metadata).stream()
            .map(metadataMapper::toMetadataResponseDTO)
            .collect(Collectors.toList());
    }

    private Metadata getParentMetadata(String parentMetadataGuid, String connectionName, String categoryType, Locale locale) {
        try {
            return getMetadataByMetadataGuid(parentMetadataGuid, locale);
        } catch (Exception e) {
            throw new LoaderServiceApplicationException(messageSource.getMessage(
                Error.LOADER_METADATA_PARENT_NOT_LOADED.getKey(),
                new Object[]{connectionName, categoryType}, locale));
        }
    }

    @Override
    public void delete(String metadataGuid, Locale locale) {
        Metadata metadata = getMetadataByMetadataGuid(metadataGuid, locale);
        metadataRepository.delete(metadata);
    }

    private Metadata getMetadataByMetadataGuid(String metadataGuid, Locale locale) {
        Object[] params = {Entity.METADATA.getName(), Entity.Field.GUID.getFieldName(), metadataGuid};
        return metadataRepository
            .findById(metadataGuid)
            .orElseThrow(() ->
                new EntityNotFoundException(
                    messageSource.getMessage(Error.ENTITY_NOT_FOUND.getKey(), params, locale)));
    }
}
