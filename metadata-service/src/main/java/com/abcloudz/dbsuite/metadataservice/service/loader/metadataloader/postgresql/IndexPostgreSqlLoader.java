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

@Component("IndexPostgreSqlLoader")
public class IndexPostgreSqlLoader implements MetadataLoader {

    @Override
    public List<Metadata> loadMetadata(LoadContext loadContext) {
        Object[] params = new Object[]{
            loadContext.getParent().extractProperty(MetadataType.SCHEMA, MetadataPropertyName.NAME),
            loadContext.getParent().extractProperty(loadContext.getParent().getType(), MetadataPropertyName.NAME)};

        List<Metadata> metadata = ((JdbcTemplate) loadContext.getDatabaseClient().getClient())
            .query(loadContext.getQuery(), (rs, rowNum) ->
                Metadata.builder()
                    .connectionGuid(loadContext.getConnection().getConnectionGuid())
                    .type(MetadataType.INDEX)
                    .category(loadContext.getCategory())
                    .properties(List.of(
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.NAME)
                            .value(rs.getString("name"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.INDEX_IS_READY)
                            .value(String.valueOf(rs.getBoolean("index_is_ready")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.INDEX_TYPE)
                            .value(rs.getString("index_type"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.IS_CLUSTERED)
                            .value(String.valueOf(rs.getBoolean("is_clustered")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.IS_FUNCTION_BASED)
                            .value(String.valueOf(rs.getBoolean("is_function_based")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.IS_UNIQUE)
                            .value(String.valueOf(rs.getBoolean("is_unique")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.IS_VALID)
                            .value(String.valueOf(rs.getBoolean("is_valid")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.R_OPTIONS)
                            .value(rs.getString("r_options"))
                            .build()))
                    .parent(loadContext.getParent())
                    .build(),
                params);
        return fillPropertiesByOwner(metadata);
    }
}
