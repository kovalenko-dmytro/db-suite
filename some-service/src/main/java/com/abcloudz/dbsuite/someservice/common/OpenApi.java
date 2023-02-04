package com.abcloudz.dbsuite.someservice.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OpenApi {

    OPEN_API_INFO_TITLE("Some Service REST API documentation");

    private final String value;
}
