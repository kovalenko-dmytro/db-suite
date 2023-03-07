package com.gmail.apachdima.dbsuite.metadataservice.util.mapper;

import com.gmail.apachdima.dbsuite.metadataservice.dto.category.BaseMetadataCategoryResponseDTO;
import com.gmail.apachdima.dbsuite.metadataservice.dto.metadata.MetadataPropertyResponseDTO;
import com.gmail.apachdima.dbsuite.metadataservice.dto.metadata.MetadataResponseDTO;
import com.gmail.apachdima.dbsuite.metadataservice.model.category.MetadataCategory;
import com.gmail.apachdima.dbsuite.metadataservice.model.category.MetadataCategoryType;
import com.gmail.apachdima.dbsuite.metadataservice.model.metadata.Metadata;
import com.gmail.apachdima.dbsuite.metadataservice.model.metadata.MetadataProperty;
import com.gmail.apachdima.dbsuite.metadataservice.model.metadata.MetadataPropertyName;
import com.gmail.apachdima.dbsuite.metadataservice.model.metadata.MetadataType;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MetadataMapper {

    @Mapping(target = "parent", source = "parent.metadataGuid")
    MetadataResponseDTO toMetadataResponseDTO(Metadata metadata);

    @Mapping(target = "metadataGuid", source = "metadata.metadataGuid")
    MetadataPropertyResponseDTO toMetadataPropertyResponseDTO(MetadataProperty metadataProperty);

    default String metadataTypeToString(MetadataType type) {
        return (Objects.isNull(type)) ? null : type.getType();
    }

    default String metadataPropertyNameToString(MetadataPropertyName name) {
        return (Objects.isNull(name)) ? null : name.getName();
    }

    default String metadataCategoryTypeToString(MetadataCategoryType type) {
        return (Objects.isNull(type)) ? null : type.getType();
    }

    default BaseMetadataCategoryResponseDTO metadataCategoryToBaseMetadataCategoryResponseDTO(MetadataCategory metadataCategory) {
        return Objects.isNull(metadataCategory)
            ? null
            : BaseMetadataCategoryResponseDTO.builder()
            .metadataCategoryGuid(metadataCategory.getMetadataCategoryGuid())
            .type(metadataCategory.getType().getType())
            .subCategories(collectSubCategories(metadataCategory))
            .build();
    }

    private List<BaseMetadataCategoryResponseDTO> collectSubCategories(MetadataCategory metadataCategory) {
        return metadataCategory.getSubCategories().stream()
            .map(subCategory -> BaseMetadataCategoryResponseDTO.builder()
                .metadataCategoryGuid(subCategory.getMetadataCategoryGuid())
                .type(subCategory.getType().getType())
                .subCategories(Collections.emptyList())
                .build())
            .collect(Collectors.toList());
    }
}
