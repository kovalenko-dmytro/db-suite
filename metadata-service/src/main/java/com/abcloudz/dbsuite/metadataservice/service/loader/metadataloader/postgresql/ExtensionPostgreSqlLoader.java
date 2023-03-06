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

@Component("ExtensionPostgreSqlLoader")
public class ExtensionPostgreSqlLoader implements MetadataLoader {

    @Override
    public List<Metadata> loadMetadata(LoadContext loadContext) {
        Object[] params =
            new Object[]{loadContext.getParent().extractProperty(MetadataType.DATABASE, MetadataPropertyName.NAME)};

        List<Metadata> metadata = ((JdbcTemplate) loadContext.getDatabaseClient().getClient())
            .query(loadContext.getQuery(), (rs, rowNum) ->
                Metadata.builder()
                    .connectionGuid(loadContext.getConnection().getConnectionGuid())
                    .type(MetadataType.EXTENSION)
                    .category(loadContext.getCategory())
                    .properties(List.of(
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.EXTENSION_OWNER_ID)
                            .value(String.valueOf(rs.getInt("extowner")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.EXTENSION_NAMESPACE_ID)
                            .value(String.valueOf(rs.getInt("extnamespace")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.EXTENSION_IS_RELOCATABLE)
                            .value(String.valueOf(rs.getBoolean("extrelocatable")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.EXTENSION_VERSION)
                            .value(rs.getString("extversion"))
                            .build()))
                    .parent(loadContext.getParent())
                    .build(),
                params);
        return fillPropertiesByOwner(metadata);
    }
}
