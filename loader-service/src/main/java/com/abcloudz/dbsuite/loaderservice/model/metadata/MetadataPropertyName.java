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
    COLLATION_NAME("collation_name"),
    INDEX_IS_READY("index_is_ready"),
    INDEX_TYPE("index_type"),
    IS_CLUSTERED("is_clustered"),
    IS_FUNCTION_BASED("is_function_based"),
    IS_UNIQUE("is_unique"),
    IS_VALID("is_valid"),
    R_OPTIONS("r_options"),
    ORDER_COLUMN("order_column"),
    ORDER_BY("order_by"),
    NULLS("nulls"),
    IS_DISABLED("is_disabled"),
    BEFORE_AFTER_INSTEAD_OF("before_after_instead_of"),
    GRANULARITY("granularity"),
    TRIGGER_EVENT("trigger_event"),
    REFERENCED_DATABASE("referenced_database"),
    TABLE_SCHEMA("table_schema"),
    TABLE_NAME("table_name"),
    CONSTRAINT_SCHEMA("constraint_schema"),
    CONSTRAINT_TYPE("constraint_type"),
    IS_DEFERRABLE("is_deferrable"),
    IS_INITIALLY_DEFERRED("is_initially_deferred"),
    CONSTRAINT_TYPE_DESC("constraint_type_desc"),
    CHECK_CLAUSE("check_clause"),
    REFERENCED_TABLE_SCHEMA("referenced_table_schema"),
    REFERENCED_TABLE_NAME("referenced_table_name"),
    REFERENCED_CONSTRAINT_SCHEMA("referenced_constraint_schema"),
    REFERENCED_CONSTRAINT_NAME("referenced_constraint_name"),
    MATCH_OPTION("match_option"),
    UPDATE_RULE("update_rule"),
    DELETE_RULE("delete_rule"),
    POSITION_IN_UNIQUE_CONSTRAINT("position_in_unique_constraint"),
    COLUMN_IN_UNIQUE_CONSTRAINT("column_in_unique_constraint"),
    CONDITION_DEFINITION("condition_definition");

    private final String name;
}
