package com.abcloudz.dbsuite.vendorservice.model.vendor;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum VendorType {

    POSTGRESQL("PostgreSql");

    private final String vendorType;
}
