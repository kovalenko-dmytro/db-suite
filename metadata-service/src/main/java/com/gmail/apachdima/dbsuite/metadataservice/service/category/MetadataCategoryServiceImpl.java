package com.gmail.apachdima.dbsuite.metadataservice.service.category;

import com.gmail.apachdima.dbsuite.metadataservice.common.Entity;
import com.gmail.apachdima.dbsuite.metadataservice.common.message.Error;
import com.gmail.apachdima.dbsuite.metadataservice.dto.category.MetadataCategoryResponseDTO;
import com.gmail.apachdima.dbsuite.metadataservice.exception.EntityNotFoundException;
import com.gmail.apachdima.dbsuite.metadataservice.model.category.MetadataCategory;
import com.gmail.apachdima.dbsuite.metadataservice.model.category.VendorType;
import com.gmail.apachdima.dbsuite.metadataservice.repository.MetadataCategoryRepository;
import com.gmail.apachdima.dbsuite.metadataservice.util.mapper.MetadataCategoryMapper;
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
        return metadataCategoryMapper.toMetadataCategoryResponseDTO(category);
    }

    @Override
    public MetadataCategoryResponseDTO findByMetadataCategoryGuid(String metadataCategoryGuid, Locale locale) {
        MetadataCategory category = getMetadataCategory(metadataCategoryGuid, locale);
        return metadataCategoryMapper.toMetadataCategoryResponseDTO(category);
    }

    @Override
    public MetadataCategory findModelByMetadataCategoryGuid(String metadataCategoryGuid, Locale locale) {
        return getMetadataCategory(metadataCategoryGuid, locale);
    }

    private MetadataCategory getMetadataCategory(String metadataCategoryGuid, Locale locale) {
        return metadataCategoryRepository
            .findById(metadataCategoryGuid)
            .orElseThrow(() ->
                new EntityNotFoundException(
                    messageSource.getMessage(Error.ENTITY_NOT_FOUND.getKey(), params(metadataCategoryGuid), locale)));
    }

    private Object[] params(String guid) {
        return new Object[]{Entity.CATEGORY.getName(), Entity.Field.GUID.getFieldName(), guid};
    }
}
