package com.gmail.apachdima.dbsuite.metadataservice.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OpenApi {

    OPEN_API_INFO_TITLE("Metadata Service REST API documentation");

    private final String value;
}
