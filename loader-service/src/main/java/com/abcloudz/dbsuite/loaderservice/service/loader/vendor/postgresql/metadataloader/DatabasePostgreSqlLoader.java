package com.abcloudz.dbsuite.loaderservice.service.loader.vendor.postgresql.metadataloader;

import com.abcloudz.dbsuite.loaderservice.model.metadata.Metadata;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component("DatabasePostgreSqlLoader")
public class DatabasePostgreSqlLoader extends AbstractPostgreSqlMetadataLoader {

    @Override
    public List<Metadata> loadMetadata(String query, Locale locale) {
        return null;
    }
}
