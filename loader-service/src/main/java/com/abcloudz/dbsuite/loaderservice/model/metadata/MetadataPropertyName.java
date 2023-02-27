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
    EXTENSION_VERSION("extension_version"),
    TABLE_IS_TYPED("is_typed_table"),
    IS_UPDATABLE("is_updatable"),
    TEXT("text"),
    HAS_INDEXES("has_indexes"),
    IS_AGGREGATE("is_aggregate"),
    IS_TRIGGER_FUNCTION("is_trigger_function"),
    RETURN_TYPE("return_type"),
    IS_UDT("is_udt"),
    TYPE_NAME("type_name"),
    START_VALUE("start_value"),
    LAST_VALUE("last_value"),
    MINIMUM_VALUE("minimum_value"),
    CHARACTER_MAXIMUM_LENGTH("character_maximum_length"),
    INCREMENT("increment"),
    NUMERIC_PRECISION("numeric_precision"),
    NUMERIC_SCALE("numeric_scale"),
    CYCLE_OPTION("cycle_option"),
    CACHE_FLAG("cache_flag"),
    CACHE_SIZE("cache_size"),
    UDT_NAME("udt_name"),
    UDT_SCHEMA("udt_schema"),
    DOMAIN_SCHEMA("domain_schema"),
    ALLOW_NULLS("allow_nulls"),
    IS_DOMAIN("is_domain"),
    IS_TYPE_ENUM("is_type_enum"),
    PARTITION_TYPE("partition_type"),
    PARTITION_COLUMNS("partition_columns"),
    TYPE_NAME_ALIAS("type_name_alias"),
    IS_IDENTITY("is_identity"),
    IS_NULLABLE("is_nullable"),
    IS_GENERATED("is_generated"),
    IDENTITY_OPTION("identity_option"),
    COLUMN_DEFAULT("column_default"),
    ORDINAL_POSITION("ordinal_position"),
    COLLATION_NAME("collation_name");

    private final String name;
}
