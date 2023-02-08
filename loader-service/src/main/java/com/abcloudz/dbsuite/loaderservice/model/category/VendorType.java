package com.abcloudz.dbsuite.loaderservice.model.category;

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
