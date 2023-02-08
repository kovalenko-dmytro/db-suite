package com.abcloudz.dbsuite.loaderservice.model.category;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MetadataCategoryType {

    SERVERS("Servers"),
    DATABASES("Databases"),
    SCHEMAS("Schemas"),
    EXTENSIONS("Extensions"),
    TABLES("Tables"),
    VIEWS("Views"),
    MATERIALIZED_VIEWS("Materialized views"),
    PROCEDURES("Procedures"),
    FUNCTIONS("Functions"),
    AGGREGATE_FUNCTIONS("Aggregate functions"),
    SEQUENCES("Sequences"),
    UDT_ALIASES("UDT aliases"),
    UDT_TABLES("UDT tables"),
    UDT_ENUMS("UDT enums"),
    COLUMNS("Columns"),
    INDEXES("Indexes"),
    TRIGGERS("Triggers"),
    PARAMETERS("Parameters"),
    PRIMARY_KEYS("Primary keys"),
    FOREIGN_KEYS("Foreign keys"),
    UNIQUE_KEYS("Unique keys"),
    CHECK_CONSTRAINTS("Check constraints");

    private final String type;
}
