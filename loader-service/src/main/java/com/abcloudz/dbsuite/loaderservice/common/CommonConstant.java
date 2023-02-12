package com.abcloudz.dbsuite.loaderservice.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CommonConstant {

    MESSAGE_SOURCE_PATH("classpath:messages/messages"),
    COLON(":"),
    DOT("."),
    EQUAL("="),
    COMMA(",");

    private final String value;
}
