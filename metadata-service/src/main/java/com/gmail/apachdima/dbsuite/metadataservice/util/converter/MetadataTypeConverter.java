package com.gmail.apachdima.dbsuite.metadataservice.util.converter;

import com.gmail.apachdima.dbsuite.metadataservice.model.metadata.MetadataType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class MetadataTypeConverter implements AttributeConverter<MetadataType, String> {

    @Override
    public String convertToDatabaseColumn(MetadataType type) {
        return Objects.isNull(type) ? null : type.getType();
    }

    @Override
    public MetadataType convertToEntityAttribute(String code) {
        return Objects.isNull(code) ? null : getType(code);
    }

    private MetadataType getType(String code) {
        return Stream.of(MetadataType.values())
            .filter(type -> type.getType().equals(code))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Metadata type <" + code + "> not found"));
    }
}
