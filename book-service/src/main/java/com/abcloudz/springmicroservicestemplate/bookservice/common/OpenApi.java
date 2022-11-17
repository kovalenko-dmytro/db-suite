package com.abcloudz.springmicroservicestemplate.bookservice.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OpenApi {

    OPEN_API_INFO_TITLE("Book Service REST API documentation"),
    OPEN_API_SECURITY_SCHEMA_NAME("bearerAuth"),
    OPEN_API_SECURITY_SCHEMA("bearer"),
    OPEN_API_SECURITY_SCHEMA_BEARER_FORMAT("JWT");

    private final String value;
}
