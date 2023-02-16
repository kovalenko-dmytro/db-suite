package com.abcloudz.dbsuite.loaderservice.service.loader.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum QueryKey {

    SERVER("metadata.query.server"),
    DATABASE("metadata.query.database"),
    SCHEMA("metadata.query.schema"),
    EXTENSION("metadata.query.extension"),
    TABLE("metadata.query.table"),
    VIEW("metadata.query.view"),
    MATERIALIZED_VIEW("metadata.query.materialized-view"),
    COLUMN("metadata.query.column"),
    TRIGGER("metadata.query.trigger"),
    PROCEDURE("metadata.query.procedure"),
    FUNCTION("metadata.query.function"),
    PARAMETER("metadata.query.parameter"),
    SEQUENCE("metadata.query.sequence"),
    INDEX("metadata.query.index"),
    CHECK_CONSTRAINT("metadata.query.check-constraint"),
    FOREIGN_KEY("metadata.query.foreign-key"),
    UNIQUE_KEY("metadata.query.unique-key"),
    PRIMARY_KEY("metadata.query.primary-key");

    private final String key;
}
