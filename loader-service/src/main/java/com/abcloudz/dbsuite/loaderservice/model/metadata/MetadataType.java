package com.abcloudz.dbsuite.loaderservice.model.metadata;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MetadataType {

    SERVER("SERVER"),
    DATABASE("DATABASE"),
    SCHEMA("SCHEMA"),
    TABLE("TABLE"),
    COLUMN("COLUMN"),
    INDEX("INDEX"),
    INDEX_COLUMN("INDEX_COLUMN"),
    CONSTRAINT_COLUMN("CONSTRAINT_COLUMN"),
    PRIMARY_KEY("PRIMARY_KEY"),
    UNIQUE_KEY("UNIQUE_KEY"),
    FOREIGN_KEY("FOREIGN_KEY"),
    CHECK_CONSTRAINT("CHECK_CONSTRAINT"),
    TRIGGER("TRIGGER"),
    VIEW("VIEW"),
    PROCEDURE("PROCEDURE"),
    FUNCTION("FUNCTION"),
    AGGREGATE_FUNCTION("AGGREGATE_FUNCTION"),
    PARAMETER("PARAMETER"),
    SYNONYM("SYNONYM"),
    PACKAGE("PACKAGE"),
    SEQUENCE("SEQUENCE"),
    RULE("RULE"),
    DEFAULT("DEFAULT"),
    USER_DEFINED_TYPE("USER_DEFINED_TYPE"),
    ASSEMBLY("ASSEMBLY"),
    EXTENSION("EXTENSION"),
    SORT_KEY("SORT_KEY"),
    DISTRIBUTION_KEY("DISTRIBUTION_KEY"),
    PARTITION("PARTITION"),
    JOIN_INDEX("JOIN_INDEX"),
    MACROS("MACROS"),
    OPERATOR("OPERATOR"),
    EXTERNAL_TABLE("EXTERNAL_TABLE"),
    SHARED_LIBRARY("SHARED_LIBRARY"),
    FLEX_TABLE("FLEX_TABLE"),
    FLEX_EXTERNAL_TABLE("FLEX_EXTERNAL_TABLE"),
    PROJECTION("PROJECTION"),
    TEXT_INDEX("TEXT_INDEX"),
    UDT_ENUM("UDT_ENUM"),
    MATERIALIZED_VIEW("MATERIALIZED_VIEW"),
    VIRTUAL_VIEW("VIRTUAL_VIEW");

    private final String type;
}
