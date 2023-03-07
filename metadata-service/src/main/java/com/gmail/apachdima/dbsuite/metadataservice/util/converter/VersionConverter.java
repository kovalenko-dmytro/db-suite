package com.gmail.apachdima.dbsuite.metadataservice.util.converter;



import com.gmail.apachdima.dbsuite.metadataservice.common.CommonConstant;
import com.gmail.apachdima.dbsuite.metadataservice.model.version.Version;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Converter(autoApply = true)
public class VersionConverter implements AttributeConverter<Version, String> {
    @Override
    public String convertToDatabaseColumn(Version version) {
        return Objects.isNull(version) ? null : version.getMajor() + CommonConstant.DOT.getValue() + version.getMinor();
    }

    @Override
    public Version convertToEntityAttribute(String s) {
        return Objects.isNull(s) ? null : new Version(s);
    }
}
