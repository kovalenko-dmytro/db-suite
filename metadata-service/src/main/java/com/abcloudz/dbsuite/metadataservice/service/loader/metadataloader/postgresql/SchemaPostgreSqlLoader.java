package com.abcloudz.dbsuite.metadataservice.service.loader.metadataloader.postgresql;

import com.abcloudz.dbsuite.metadataservice.dto.loader.LoadContext;
import com.abcloudz.dbsuite.metadataservice.model.metadata.Metadata;
import com.abcloudz.dbsuite.metadataservice.model.metadata.MetadataProperty;
import com.abcloudz.dbsuite.metadataservice.model.metadata.MetadataPropertyName;
import com.abcloudz.dbsuite.metadataservice.model.metadata.MetadataType;
import com.abcloudz.dbsuite.metadataservice.service.loader.metadataloader.MetadataLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("SchemaPostgreSqlLoader")
public class SchemaPostgreSqlLoader implements MetadataLoader {

    @Override
    public List<Metadata> loadMetadata(LoadContext loadContext) {
        Object[] params =
            new Object[]{loadContext.getParent().extractProperty(MetadataType.DATABASE, MetadataPropertyName.NAME)};

        List<Metadata> metadata = ((JdbcTemplate) loadContext.getDatabaseClient().getClient())
            .query(loadContext.getQuery(), (rs, rowNum) ->
                Metadata.builder()
                    .connectionGuid(loadContext.getConnection().getConnectionGuid())
                    .type(MetadataType.SCHEMA)
                    .category(loadContext.getCategory())
                    .properties(List.of(
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.NAME)
                            .value(rs.getString("name"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.IS_SYSTEM)
                            .value(String.valueOf(rs.getBoolean("is_system")))
                            .build()))
                    .parent(loadContext.getParent())
                    .build(),
                params);
        return fillPropertiesByOwner(metadata);
    }
}
