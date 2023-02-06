package com.abcloudz.dbsuite.vendorservice.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OpenApi {

    OPEN_API_INFO_TITLE("Database Vendor Service REST API documentation");

    private final String value;
}
