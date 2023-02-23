package com.abcloudz.dbsuite.loaderservice.service.loader.metadataloader.postgresql;

import com.abcloudz.dbsuite.loaderservice.dto.loader.LoadContext;
import com.abcloudz.dbsuite.loaderservice.model.metadata.Metadata;
import com.abcloudz.dbsuite.loaderservice.model.metadata.MetadataProperty;
import com.abcloudz.dbsuite.loaderservice.model.metadata.MetadataPropertyName;
import com.abcloudz.dbsuite.loaderservice.model.metadata.MetadataType;
import com.abcloudz.dbsuite.loaderservice.service.loader.metadataloader.MetadataLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("TablePostgreSqlLoader")
public class TablePostgreSqlLoader implements MetadataLoader {

    @Override
    public List<Metadata> loadMetadata(LoadContext loadContext) {
        Object[] params = new Object[]{
            loadContext.getParent().extractProperty(MetadataType.DATABASE, MetadataPropertyName.NAME),
            loadContext.getParent().extractProperty(MetadataType.SCHEMA, MetadataPropertyName.NAME)};

        List<Metadata> metadata = ((JdbcTemplate) loadContext.getDatabaseClient().getClient())
            .query(loadContext.getQuery(), (rs, rowNum) ->
                Metadata.builder()
                    .connectionGuid(loadContext.getConnection().getConnectionGuid())
                    .type(MetadataType.TABLE)
                    .category(loadContext.getCategory())
                    .properties(List.of(
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.NAME)
                            .value(rs.getString("name"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.TABLE_IS_TYPED)
                            .value(String.valueOf(rs.getBoolean("is_typed_table")))
                            .build()))
                    .parent(loadContext.getParent())
                    .build(),
                params);
        return fillPropertiesByOwner(metadata);
    }
}
