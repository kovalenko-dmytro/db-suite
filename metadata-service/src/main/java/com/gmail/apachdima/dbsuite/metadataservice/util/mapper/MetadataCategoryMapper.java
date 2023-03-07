package com.gmail.apachdima.dbsuite.metadataservice.util.mapper;

import com.gmail.apachdima.dbsuite.metadataservice.dto.category.MetadataCategoryResponseDTO;
import com.gmail.apachdima.dbsuite.metadataservice.model.category.MetadataCategory;
import com.gmail.apachdima.dbsuite.metadataservice.model.category.MetadataCategoryType;
import com.gmail.apachdima.dbsuite.metadataservice.model.category.VendorType;
import com.gmail.apachdima.dbsuite.metadataservice.model.version.Version;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MetadataCategoryMapper {

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

    default List<MetadataCategoryResponseDTO> metadataCategoryListToMetadataCategoryResponseDTOList(List<MetadataCategory> list) {
        if (Objects.isNull(list)) {
            return null;
        }
        return list.stream()
            .map(category -> MetadataCategoryResponseDTO.builder()
                .metadataCategoryGuid(category.getMetadataCategoryGuid())
                .type(category.getType().getType())
                .root(category.isRoot())
                .vendorType(category.getVendorType().getVendorType())
                .versionFrom(category.getVersionFrom().toString())
                .addedAt(category.getAddedAt())
                .subCategories(Collections.emptyList())
                .build())
            .collect(Collectors.toList());
    }
}
