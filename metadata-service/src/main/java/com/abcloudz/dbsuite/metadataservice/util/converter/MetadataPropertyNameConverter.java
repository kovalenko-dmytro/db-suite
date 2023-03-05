package com.abcloudz.dbsuite.metadataservice.util.converter;

import com.abcloudz.dbsuite.metadataservice.model.metadata.MetadataPropertyName;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class MetadataPropertyNameConverter implements AttributeConverter<MetadataPropertyName, String> {

    @Override
    public String convertToDatabaseColumn(MetadataPropertyName name) {
        return Objects.isNull(name) ? null : name.getName();
    }

    @Override
    public MetadataPropertyName convertToEntityAttribute(String code) {
        return Objects.isNull(code) ? null : getName(code);
    }

    private MetadataPropertyName getName(String code) {
        return Stream.of(MetadataPropertyName.values())
            .filter(name -> name.getName().equals(code))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Metadata property name <" + code + "> not found"));
    }
}
