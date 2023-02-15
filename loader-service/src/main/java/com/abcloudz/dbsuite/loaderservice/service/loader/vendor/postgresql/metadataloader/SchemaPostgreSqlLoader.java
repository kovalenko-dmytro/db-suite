package com.abcloudz.dbsuite.loaderservice.service.loader.vendor.postgresql.metadataloader;

import com.abcloudz.dbsuite.loaderservice.model.category.MetadataCategory;
import com.abcloudz.dbsuite.loaderservice.model.metadata.Metadata;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component("SchemaPostgreSqlLoader")
@RequiredArgsConstructor
public class SchemaPostgreSqlLoader extends AbstractPostgreSqlMetadataLoader {

    @Override
    public Metadata loadMetadata(MetadataCategory category, Metadata parent, Locale locale) {
        return null;
    }
}
