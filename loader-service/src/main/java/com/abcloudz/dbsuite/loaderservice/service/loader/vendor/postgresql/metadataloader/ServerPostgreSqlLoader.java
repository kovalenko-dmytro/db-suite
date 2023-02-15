package com.abcloudz.dbsuite.loaderservice.service.loader.vendor.postgresql.metadataloader;

import com.abcloudz.dbsuite.loaderservice.model.category.MetadataCategory;
import com.abcloudz.dbsuite.loaderservice.model.metadata.Metadata;
import com.abcloudz.dbsuite.loaderservice.model.metadata.MetadataType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component("ServerPostgreSqlLoader")
@RequiredArgsConstructor
public class ServerPostgreSqlLoader extends AbstractPostgreSqlMetadataLoader {

    @Override
    public Metadata loadMetadata(MetadataCategory category, Metadata parent, Locale locale) {
        return Metadata.builder().type(MetadataType.SERVER).category(category).parent(parent).build();
    }
}
