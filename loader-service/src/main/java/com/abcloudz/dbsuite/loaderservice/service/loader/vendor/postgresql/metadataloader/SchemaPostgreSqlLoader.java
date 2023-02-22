package com.abcloudz.dbsuite.loaderservice.service.loader.vendor.postgresql.metadataloader;

import com.abcloudz.dbsuite.loaderservice.model.metadata.Metadata;
import com.abcloudz.dbsuite.loaderservice.model.metadata.MetadataProperty;
import com.abcloudz.dbsuite.loaderservice.model.metadata.MetadataPropertyName;
import com.abcloudz.dbsuite.loaderservice.model.metadata.MetadataType;
import com.abcloudz.dbsuite.loaderservice.service.loader.RDBMSMetadataLoader;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component("SchemaPostgreSqlLoader")
public class SchemaPostgreSqlLoader extends RDBMSMetadataLoader {

    @Override
    public List<Metadata> loadMetadata(String query, Metadata parent, Locale locale) {
        Object[] params = new Object[]{parent.extractProperty(MetadataType.DATABASE, MetadataPropertyName.NAME)};
        return getJdbcTemplate()
            .query(query, (rs, rowNum) ->
                Metadata.builder()
                    .type(MetadataType.SCHEMA)
                    .properties(List.of(
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.NAME)
                            .value(rs.getString("name"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.IS_SYSTEM)
                            .value(String.valueOf(rs.getBoolean("is_system")))
                            .build()))
                    .build(),
                params);
    }
}
