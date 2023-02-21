package com.abcloudz.dbsuite.loaderservice.model.metadata;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MetadataPropertyName {

    NAME("name"),
    VERSION("version"),
    UUID("uuid"),
    COLLATION("collation"),
    DEFAULT_SCHEMA("default_schema"),
    IS_SYSTEM("is_system"),
    EXTENSION_OWNER_ID("extension_owner_id"),
    EXTENSION_NAMESPACE_ID("extension_namespace_id"),
    EXTENSION_IS_RELOCATABLE("extension_is_relocatable"),
    EXTENSION_VERSION("extension_version");

    private final String name;
}
