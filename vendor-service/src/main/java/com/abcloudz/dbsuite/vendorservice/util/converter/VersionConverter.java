package com.abcloudz.dbsuite.vendorservice.util.converter;




import com.abcloudz.dbsuite.vendorservice.common.CommonConstant;
import com.abcloudz.dbsuite.vendorservice.model.connection.Version;

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
    public Version convertToEntityAttribute(String version) {
        return Objects.isNull(version) ? null : new Version(version);
    }
}
