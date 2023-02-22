package com.abcloudz.dbsuite.loaderservice.service.loader.vendor.postgresql.metadataloader;

import com.abcloudz.dbsuite.loaderservice.model.metadata.Metadata;
import com.abcloudz.dbsuite.loaderservice.model.metadata.MetadataProperty;
import com.abcloudz.dbsuite.loaderservice.model.metadata.MetadataPropertyName;
import com.abcloudz.dbsuite.loaderservice.model.metadata.MetadataType;
import com.abcloudz.dbsuite.loaderservice.service.loader.RDBMSMetadataLoader;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component("ViewPostgreSqlLoader")
public class ViewPostgreSqlLoader extends RDBMSMetadataLoader {

    @Override
    public List<Metadata> loadMetadata(String query, Metadata parent, Locale locale) {
        Object[] params = new Object[]{
            parent.extractProperty(MetadataType.DATABASE, MetadataPropertyName.NAME),
            parent.extractProperty(MetadataType.SCHEMA, MetadataPropertyName.NAME)};
        return getJdbcTemplate()
            .query(query, (rs, rowNum) ->
                Metadata.builder()
                    .type(MetadataType.VIEW)
                    .properties(List.of(
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.NAME)
                            .value(rs.getString("name"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.IS_UPDATABLE)
                            .value(String.valueOf(rs.getBoolean("is_updatable")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.TEXT)
                            .value(rs.getString("text"))
                            .build()))
                    .build(),
                params);
    }
}
