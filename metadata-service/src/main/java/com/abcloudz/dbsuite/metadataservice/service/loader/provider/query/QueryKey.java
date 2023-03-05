package com.abcloudz.dbsuite.metadataservice.service.loader.provider.query;

import com.abcloudz.dbsuite.metadataservice.model.category.MetadataCategoryType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum QueryKey {

    SERVER(MetadataCategoryType.SERVERS, "metadata.query.server"),
    DATABASE(MetadataCategoryType.DATABASES, "metadata.query.database"),
    SCHEMA(MetadataCategoryType.SCHEMAS, "metadata.query.schema"),
    EXTENSION(MetadataCategoryType.EXTENSIONS, "metadata.query.extension"),
    TABLE(MetadataCategoryType.TABLES, "metadata.query.table"),
    VIEW(MetadataCategoryType.VIEWS, "metadata.query.view"),
    MATERIALIZED_VIEW(MetadataCategoryType.MATERIALIZED_VIEWS, "metadata.query.materialized-view"),
    COLUMN(MetadataCategoryType.COLUMNS, "metadata.query.column"),
    TRIGGER(MetadataCategoryType.TRIGGERS, "metadata.query.trigger"),
    PROCEDURE(MetadataCategoryType.PROCEDURES, "metadata.query.procedure"),
    FUNCTION(MetadataCategoryType.FUNCTIONS, "metadata.query.function"),
    AGGREGATE_FUNCTION(MetadataCategoryType.AGGREGATE_FUNCTIONS, "metadata.query.aggregate-function"),
    PARAMETER(MetadataCategoryType.PARAMETERS, "metadata.query.parameter"),
    SEQUENCE(MetadataCategoryType.SEQUENCES, "metadata.query.sequence"),
    USER_DEFINED_TYPE(MetadataCategoryType.USER_DEFINED_TYPES, "metadata.query.udt"),
    PARTITION(MetadataCategoryType.PARTITIONS, "metadata.query.partition"),
    INDEX(MetadataCategoryType.INDEXES, "metadata.query.index"),
    INDEX_COLUMN(MetadataCategoryType.INDEX_COLUMNS, "metadata.query.index-column"),
    CHECK_CONSTRAINT(MetadataCategoryType.CHECK_CONSTRAINTS, "metadata.query.check-constraint"),
    FOREIGN_KEY(MetadataCategoryType.FOREIGN_KEYS, "metadata.query.constraint"),
    UNIQUE_KEY(MetadataCategoryType.UNIQUE_KEYS, "metadata.query.constraint"),
    PRIMARY_KEY(MetadataCategoryType.PRIMARY_KEYS, "metadata.query.constraint"),
    CONSTRAINT_COLUMN(MetadataCategoryType.CONSTRAINT_COLUMNS, "metadata.query.constraint-column");

    private final MetadataCategoryType categoryType;
    private final String key;

    public static QueryKey obtainQueryKey(MetadataCategoryType type) {
        return Arrays.stream(QueryKey.values())
            .filter(queryKey -> queryKey.getCategoryType().equals(type))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Query key for category type <" + type + "> not found"));
    }
}
