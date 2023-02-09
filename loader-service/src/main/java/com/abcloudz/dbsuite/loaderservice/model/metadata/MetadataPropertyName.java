package com.abcloudz.dbsuite.loaderservice.model.metadata;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MetadataPropertyName {

    NAME("name"),
    VERSION("version"),
    UUID("uuid");

    private final String name;
}
