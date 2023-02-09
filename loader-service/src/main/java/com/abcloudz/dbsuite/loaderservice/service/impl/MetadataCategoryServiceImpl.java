package com.abcloudz.dbsuite.loaderservice.service.impl;

import com.abcloudz.dbsuite.loaderservice.common.Entity;
import com.abcloudz.dbsuite.loaderservice.common.message.Error;
import com.abcloudz.dbsuite.loaderservice.dto.category.MetadataCategoryResponseDTO;
import com.abcloudz.dbsuite.loaderservice.exception.EntityNotFoundException;
import com.abcloudz.dbsuite.loaderservice.model.category.MetadataCategory;
import com.abcloudz.dbsuite.loaderservice.model.category.VendorType;
import com.abcloudz.dbsuite.loaderservice.repository.MetadataCategoryRepository;
import com.abcloudz.dbsuite.loaderservice.service.MetadataCategoryService;
import com.abcloudz.dbsuite.loaderservice.util.mapper.MetadataCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MetadataCategoryServiceImpl implements MetadataCategoryService {

    private final MetadataCategoryRepository metadataCategoryRepository;
    private final MessageSource messageSource;
    private final MetadataCategoryMapper metadataCategoryMapper;

    @Override
    public MetadataCategoryResponseDTO findRootMetadataCategory(String vendorType, Locale locale) {
        MetadataCategory category = metadataCategoryRepository
            .findByRoot(true).stream()
            .filter(metadataCategory -> metadataCategory.getVendorType().equals(VendorType.getType(vendorType)))
            .findFirst()
            .orElseThrow(() ->
                new EntityNotFoundException(
                    messageSource.getMessage(Error.CATEGORY_ROOT_NOT_FOUND.getKey(), new Object[]{vendorType}, locale)));
        return metadataCategoryMapper.clearSubChildren(metadataCategoryMapper.toMetadataCategoryResponseDTO(category));
    }

    @Override
    public MetadataCategoryResponseDTO findByMetadataCategoryGuid(String metadataCategoryGuid, Locale locale) {
        MetadataCategory category = metadataCategoryRepository
            .findById(metadataCategoryGuid)
            .orElseThrow(() ->
                new EntityNotFoundException(
                    messageSource.getMessage(Error.ENTITY_NOT_FOUND.getKey(), params(metadataCategoryGuid), locale)));
        return metadataCategoryMapper.clearSubChildren(metadataCategoryMapper.toMetadataCategoryResponseDTO(category));
    }

    private Object[] params(String guid) {
        return new Object[]{Entity.CATEGORY.getName(), Entity.Field.GUID.getFieldName(), guid};
    }
}
