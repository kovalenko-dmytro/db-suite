package com.abcloudz.dbsuite.loaderservice.service.loader;

import com.abcloudz.dbsuite.loaderservice.model.metadata.Metadata;

import java.util.List;
import java.util.Locale;

public interface MetadataLoader<DC> {

    List<Metadata> loadMetadata(String query, Metadata parent, Locale locale);
    void setDatabaseClient(DC databaseClient);
}
