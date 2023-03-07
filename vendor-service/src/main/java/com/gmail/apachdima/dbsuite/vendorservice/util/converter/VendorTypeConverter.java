package com.gmail.apachdima.dbsuite.vendorservice.util.converter;

import com.gmail.apachdima.dbsuite.vendorservice.model.vendor.VendorType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class VendorTypeConverter implements AttributeConverter<VendorType, String> {

    @Override
    public String convertToDatabaseColumn(VendorType vendorType) {
        return Objects.isNull(vendorType) ? null : vendorType.getVendorType();
    }

    @Override
    public VendorType convertToEntityAttribute(String code) {
        return Objects.isNull(code) ? null : getType(code);
    }

    private VendorType getType(String code) {
        return Stream.of(VendorType.values())
            .filter(type -> type.getVendorType().equals(code))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Vendor type <" + code + "> not found"));
    }
}
