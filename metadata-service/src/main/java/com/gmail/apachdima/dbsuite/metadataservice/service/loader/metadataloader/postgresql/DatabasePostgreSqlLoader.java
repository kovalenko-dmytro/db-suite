package com.gmail.apachdima.dbsuite.metadataservice.service.loader.metadataloader.postgresql;

import com.gmail.apachdima.dbsuite.metadataservice.dto.loader.LoadContext;
import com.gmail.apachdima.dbsuite.metadataservice.model.metadata.Metadata;
import com.gmail.apachdima.dbsuite.metadataservice.model.metadata.MetadataProperty;
import com.gmail.apachdima.dbsuite.metadataservice.model.metadata.MetadataPropertyName;
import com.gmail.apachdima.dbsuite.metadataservice.model.metadata.MetadataType;
import com.gmail.apachdima.dbsuite.metadataservice.service.loader.metadataloader.MetadataLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("DatabasePostgreSqlLoader")
public class DatabasePostgreSqlLoader implements MetadataLoader {

    @Override
    public List<Metadata> loadMetadata(LoadContext loadContext) {
        List<Metadata> metadata = ((JdbcTemplate) loadContext.getDatabaseClient().getClient())
            .query(loadContext.getQuery(), (rs, rowNum) ->
                Metadata.builder()
                    .connectionGuid(loadContext.getConnection().getConnectionGuid())
                    .type(MetadataType.DATABASE)
                    .category(loadContext.getCategory())
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
                    .parent(loadContext.getParent())
                    .build());
        return fillPropertiesByOwner(metadata);
    }
}
