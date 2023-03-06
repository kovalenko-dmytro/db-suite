package com.abcloudz.dbsuite.metadataservice.util.converter;

import com.abcloudz.dbsuite.metadataservice.model.category.MetadataCategoryType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class MetadataCategoryTypeConverter implements AttributeConverter<MetadataCategoryType, String> {

    @Override
    public String convertToDatabaseColumn(MetadataCategoryType type) {
        return Objects.isNull(type) ? null : type.getType();
    }

    @Override
    public MetadataCategoryType convertToEntityAttribute(String code) {
        return Objects.isNull(code) ? null : getType(code);
    }

    private MetadataCategoryType getType(String code) {
        return Stream.of(MetadataCategoryType.values())
            .filter(type -> type.getType().equals(code))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Metadata category type <" + code + "> not found"));
    }
}
