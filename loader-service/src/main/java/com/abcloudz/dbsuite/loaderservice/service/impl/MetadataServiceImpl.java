package com.abcloudz.dbsuite.loaderservice.service.impl;

import com.abcloudz.dbsuite.loaderservice.common.Entity;
import com.abcloudz.dbsuite.loaderservice.common.message.Error;
import com.abcloudz.dbsuite.loaderservice.dto.metadata.MetadataResponseDTO;
import com.abcloudz.dbsuite.loaderservice.exception.EntityNotFoundException;
import com.abcloudz.dbsuite.loaderservice.model.metadata.Metadata;
import com.abcloudz.dbsuite.loaderservice.repository.MetadataRepository;
import com.abcloudz.dbsuite.loaderservice.service.MetadataService;
import com.abcloudz.dbsuite.loaderservice.util.mapper.MetadataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MetadataServiceImpl implements MetadataService {

    private final MetadataRepository metadataRepository;
    private final MessageSource messageSource;
    private final MetadataMapper metadataMapper;

    @Override
    public MetadataResponseDTO findByMetadataGuid(String metadataGuid, Locale locale) {
        Metadata metadata = getMetadataByMetadataGuid(metadataGuid, locale);
        return metadataMapper.clearSubChildren(metadataMapper.toMetadataResponseDTO(metadata));
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
