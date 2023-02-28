package com.abcloudz.dbsuite.vendorservice.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Entity {

    VENDOR("Vendor"),
    CONNECTION("Connection");

    private final String name;

    @AllArgsConstructor
    @Getter
    public enum VendorField {

        VENDOR_GUID("guid");

        private final String fieldName;
    }

    @AllArgsConstructor
    @Getter
    public enum ConnectionField {

        CONNECTION_GUID("connectionGuid"),
        VENDOR_GUID("vendorGuid");

        private final String fieldName;
    }
}
