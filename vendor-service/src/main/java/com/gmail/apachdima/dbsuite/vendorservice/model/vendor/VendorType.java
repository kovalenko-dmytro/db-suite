package com.gmail.apachdima.dbsuite.vendorservice.model.vendor;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum VendorType {

    POSTGRESQL("PostgreSql"),
    MYSQL("MySql"),
    MONGO_DB("MongoDB");

    private final String vendorType;
}
