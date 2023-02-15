package com.abcloudz.dbsuite.loaderservice.util.mapper;

import com.abcloudz.dbsuite.loaderservice.dto.category.BaseMetadataCategoryResponseDTO;
import com.abcloudz.dbsuite.loaderservice.dto.metadata.MetadataPropertyResponseDTO;
import com.abcloudz.dbsuite.loaderservice.dto.metadata.MetadataResponseDTO;
import com.abcloudz.dbsuite.loaderservice.model.category.MetadataCategoryType;
import com.abcloudz.dbsuite.loaderservice.model.metadata.Metadata;
import com.abcloudz.dbsuite.loaderservice.model.metadata.MetadataProperty;
import com.abcloudz.dbsuite.loaderservice.model.metadata.MetadataPropertyName;
import com.abcloudz.dbsuite.loaderservice.model.metadata.MetadataType;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MetadataMapper {

    @Mapping(target="parent", source="parent.metadataGuid")
    MetadataResponseDTO toMetadataResponseDTO(Metadata metadata);

    @Mapping(target="metadataGuid", source="metadata.metadataGuid")
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

    default MetadataResponseDTO clearSubChildren(MetadataResponseDTO source) {
        List<MetadataResponseDTO> children = Collections.emptyList();
        List<BaseMetadataCategoryResponseDTO> subCategories = Collections.emptyList();
        if (Objects.nonNull(source.getChildren())) {
            children = source.getChildren().stream()
                .peek(metadataResponseDTO -> metadataResponseDTO.setChildren(Collections.emptyList()))
                .collect(Collectors.toList());
        }
        if (Objects.nonNull(source.getCategory().getSubCategories())) {
            subCategories = source.getCategory().getSubCategories().stream()
                .peek(category -> category.setSubCategories(Collections.emptyList()))
                .collect(Collectors.toList());
        }
        source.setChildren(children);
        source.getCategory().setSubCategories(subCategories);
        return source;
    }
}
