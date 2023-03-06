package com.abcloudz.dbsuite.metadataservice.service.metadata;

import com.abcloudz.dbsuite.metadataservice.client.VendorServiceClient;
import com.abcloudz.dbsuite.metadataservice.common.Entity;
import com.abcloudz.dbsuite.metadataservice.common.message.Error;
import com.abcloudz.dbsuite.metadataservice.dto.category.BaseMetadataCategoryResponseDTO;
import com.abcloudz.dbsuite.metadataservice.dto.connection.ConnectionResponseDTO;
import com.abcloudz.dbsuite.metadataservice.dto.loader.LoadContext;
import com.abcloudz.dbsuite.metadataservice.dto.metadata.LoadMetadataRequestDTO;
import com.abcloudz.dbsuite.metadataservice.dto.metadata.MetadataResponseDTO;
import com.abcloudz.dbsuite.metadataservice.exception.EntityNotFoundException;
import com.abcloudz.dbsuite.metadataservice.exception.MetadataServiceApplicationException;
import com.abcloudz.dbsuite.metadataservice.model.category.MetadataCategory;
import com.abcloudz.dbsuite.metadataservice.model.category.MetadataCategoryType;
import com.abcloudz.dbsuite.metadataservice.model.metadata.Metadata;
import com.abcloudz.dbsuite.metadataservice.repository.MetadataRepository;
import com.abcloudz.dbsuite.metadataservice.service.category.MetadataCategoryService;
import com.abcloudz.dbsuite.metadataservice.service.loader.ILoader;
import com.abcloudz.dbsuite.metadataservice.util.mapper.MetadataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    @Transactional(rollbackFor = Exception.class)
    public List<MetadataResponseDTO> load(LoadMetadataRequestDTO request, boolean full, Locale locale) {
        List<MetadataResponseDTO> metadata = load(request, locale);
        if (!full) {
            return metadata;
        }
        for (MetadataResponseDTO parentMetadata : metadata) {
            parentMetadata.setChildren(new ArrayList<>());
            String parentMetadataGuid = parentMetadata.getMetadataGuid();
            for (BaseMetadataCategoryResponseDTO category: parentMetadata.getCategory().getSubCategories()) {
                request.setParentMetadataGuid(parentMetadataGuid);
                request.setMetadataCategoryGuid(category.getMetadataCategoryGuid());
                parentMetadata.getChildren().addAll(load(request, true, locale));
            }
        }
        return metadata;
    }

    @Transactional(rollbackFor = Exception.class)
    private List<MetadataResponseDTO> load(LoadMetadataRequestDTO request, Locale locale) {
        ConnectionResponseDTO connection = vendorServiceClient.findByGuid(request.getConnectionGuid(), locale);
        MetadataCategory category = metadataCategoryService
            .findModelByMetadataCategoryGuid(request.getMetadataCategoryGuid(), locale);
        Metadata parent = category.getType().equals(MetadataCategoryType.SERVERS)
            ? null
            : getParentMetadata(request.getParentMetadataGuid(), connection.getConnectionName(), category.getType().getType(), locale);

        LoadContext context = LoadContext.builder().connection(connection).category(category).parent(parent).build();
        List<Metadata> metadata = loader.load(context, locale);

       metadataRepository
            .deleteByConnectionGuidAndCategory_metadataCategoryGuidAndParent_metadataGuid(
                request.getConnectionGuid(),
                request.getMetadataCategoryGuid(),
                request.getParentMetadataGuid());

        return metadataRepository.saveAll(metadata).stream()
            .map(metadataMapper::toMetadataResponseDTO)
            .collect(Collectors.toList());
    }

    private Metadata getParentMetadata(String parentMetadataGuid, String connectionName, String categoryType, Locale locale) {
        try {
            return getMetadataByMetadataGuid(parentMetadataGuid, locale);
        } catch (Exception e) {
            throw new MetadataServiceApplicationException(messageSource.getMessage(
                Error.LOADER_METADATA_PARENT_NOT_LOADED.getKey(),
                new Object[]{connectionName, categoryType}, locale));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String metadataGuid, Locale locale) {
        Metadata metadata = getMetadataByMetadataGuid(metadataGuid, locale);
        metadataRepository.delete(metadata.getMetadataGuid());
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
