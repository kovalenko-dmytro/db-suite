package com.abcloudz.dbsuite.loaderservice.util.mapper;

import com.abcloudz.dbsuite.loaderservice.dto.category.MetadataCategoryResponseDTO;
import com.abcloudz.dbsuite.loaderservice.model.category.MetadataCategory;
import com.abcloudz.dbsuite.loaderservice.model.category.MetadataCategoryType;
import com.abcloudz.dbsuite.loaderservice.model.category.VendorType;
import com.abcloudz.dbsuite.loaderservice.model.version.Version;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MetadataCategoryMapper {

    @Mapping(target = "parent", source = "parent.metadataCategoryGuid")
    MetadataCategoryResponseDTO toMetadataCategoryResponseDTO(MetadataCategory category);

    default String metadataCategoryTypeToString(MetadataCategoryType type) {
        return (Objects.isNull(type)) ? null : type.getType();
    }

    default String versionToString(Version version) {
        return (Objects.isNull(version)) ? null : version.toString();
    }

    default String vendorTypeToString(VendorType type) {
        return (Objects.isNull(type)) ? null : type.getVendorType();
    }

    default MetadataCategoryResponseDTO clearSubChildren(MetadataCategoryResponseDTO source) {
        List<MetadataCategoryResponseDTO> subCategories = source.getSubCategories().stream()
            .peek(metadataCategoryResponseDTO -> metadataCategoryResponseDTO.setSubCategories(Collections.emptyList()))
            .collect(Collectors.toList());
        source.setSubCategories(subCategories);
        return source;
    }
}
