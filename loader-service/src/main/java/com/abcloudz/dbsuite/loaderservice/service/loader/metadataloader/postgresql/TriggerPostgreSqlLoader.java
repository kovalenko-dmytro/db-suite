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

@Component("TriggerPostgreSqlLoader")
public class TriggerPostgreSqlLoader implements MetadataLoader {

    @Override
    public List<Metadata> loadMetadata(LoadContext loadContext) {
        Object[] params = new Object[]{
            loadContext.getParent().extractProperty(MetadataType.SCHEMA, MetadataPropertyName.NAME),
            loadContext.getParent().extractProperty(loadContext.getParent().getType(), MetadataPropertyName.NAME)};

        List<Metadata> metadata = ((JdbcTemplate) loadContext.getDatabaseClient().getClient())
            .query(loadContext.getQuery(), (rs, rowNum) ->
                Metadata.builder()
                    .connectionGuid(loadContext.getConnection().getConnectionGuid())
                    .type(MetadataType.TRIGGER)
                    .category(loadContext.getCategory())
                    .properties(List.of(
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.NAME)
                            .value(rs.getString("name"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.IS_DISABLED)
                            .value(String.valueOf(rs.getBoolean("is_disabled")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.BEFORE_AFTER_INSTEAD_OF)
                            .value(rs.getString("before_after_instead_of"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.GRANULARITY)
                            .value(rs.getString("granularity"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.TRIGGER_EVENT)
                            .value(rs.getString("trigger_event"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.TEXT)
                            .value(rs.getString("text"))
                            .build()))
                    .parent(loadContext.getParent())
                    .build(),
                params);
        return fillPropertiesByOwner(metadata);
    }
}
