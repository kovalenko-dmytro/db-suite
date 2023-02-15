package com.abcloudz.dbsuite.loaderservice.service.loader;

import com.abcloudz.dbsuite.loaderservice.model.category.MetadataCategory;
import com.abcloudz.dbsuite.loaderservice.model.metadata.Metadata;

import java.util.Locale;

public interface MetadataLoader<DC> {

    Metadata loadMetadata(MetadataCategory category, Metadata parent, Locale locale);
    void setDatabaseClient(DC databaseClient);
}
