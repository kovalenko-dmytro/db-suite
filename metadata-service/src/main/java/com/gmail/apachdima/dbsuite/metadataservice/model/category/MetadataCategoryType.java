package com.gmail.apachdima.dbsuite.metadataservice.model.category;

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
    USER_DEFINED_TYPES("User defined types"),
    PARTITIONS("Partitions"),
    COLUMNS("Columns"),
    INDEXES("Indexes"),
    INDEX_COLUMNS("Index columns"),
    CONSTRAINT_COLUMNS("Constraint columns"),
    TRIGGERS("Triggers"),
    PARAMETERS("Parameters"),
    PRIMARY_KEYS("Primary keys"),
    FOREIGN_KEYS("Foreign keys"),
    UNIQUE_KEYS("Unique keys"),
    CHECK_CONSTRAINTS("Check constraints");

    private final String type;
}
