package com.abcloudz.dbsuite.loaderservice.service.loader.vendor.postgresql.metadataloader;

import com.abcloudz.dbsuite.loaderservice.model.metadata.Metadata;
import com.abcloudz.dbsuite.loaderservice.model.metadata.MetadataProperty;
import com.abcloudz.dbsuite.loaderservice.model.metadata.MetadataPropertyName;
import com.abcloudz.dbsuite.loaderservice.model.metadata.MetadataType;
import com.abcloudz.dbsuite.loaderservice.service.loader.RDBMSMetadataLoader;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component("DatabasePostgreSqlLoader")
public class DatabasePostgreSqlLoader extends RDBMSMetadataLoader {

    @Override
    public List<Metadata> loadMetadata(String query, Metadata parent, Locale locale) {
        return getJdbcTemplate()
            .query(query, (rs, rowNum) ->
                Metadata.builder()
                    .type(MetadataType.DATABASE)
                    .properties(List.of(
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.NAME)
                            .value(rs.getString("name"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.COLLATION)
                            .value(rs.getString("collation"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.DEFAULT_SCHEMA)
                            .value(rs.getString("default_schema"))
                            .build()))
                    .build());
    }
}
