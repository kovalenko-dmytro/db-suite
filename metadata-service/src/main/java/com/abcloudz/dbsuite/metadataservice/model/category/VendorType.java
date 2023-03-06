package com.abcloudz.dbsuite.metadataservice.model.category;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum VendorType {

    POSTGRESQL("PostgreSql"),
    MYSQL("MySql"),
    MONGO_DB("MongoDB");

    private final String vendorType;

    public static VendorType getType(String code) {
        return Stream.of(VendorType.values())
            .filter(type -> type.getVendorType().equals(code))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Vendor type <" + code + "> not found"));
    }
}
