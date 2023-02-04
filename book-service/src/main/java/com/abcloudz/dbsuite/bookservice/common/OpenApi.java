package com.abcloudz.dbsuite.bookservice.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OpenApi {

    OPEN_API_INFO_TITLE("Book Service REST API documentation");

    private final String value;
}
