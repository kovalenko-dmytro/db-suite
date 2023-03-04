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

@Component("ParameterPostgreSqlLoader")
public class ParameterPostgreSqlLoader implements MetadataLoader {

    @Override
    public List<Metadata> loadMetadata(LoadContext loadContext) {
        Object[] params = new Object[]{
            loadContext.getParent().extractProperty(MetadataType.SCHEMA, MetadataPropertyName.NAME),
            loadContext.getParent().extractProperty(loadContext.getParent().getType(), MetadataPropertyName.NAME)};

        List<Metadata> metadata = ((JdbcTemplate) loadContext.getDatabaseClient().getClient())
            .query(loadContext.getQuery(), (rs, rowNum) ->
                Metadata.builder()
                    .connectionGuid(loadContext.getConnection().getConnectionGuid())
                    .type(MetadataType.PARAMETER)
                    .category(loadContext.getCategory())
                    .properties(List.of(
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.NAME)
                            .value(rs.getString("name"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.IS_NO_NAME)
                            .value(hasColumn(rs, "is_no_name")
                                ? String.valueOf(rs.getBoolean("is_no_name")) : null)
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.IS_RETURNS)
                            .value(String.valueOf(rs.getBoolean("is_returns")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.TYPE_NAME)
                            .value(rs.getString("type_name"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.ARGUMENT_MODE)
                            .value(rs.getString("argument_mode"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.ARGUMENT_ORDER)
                            .value(String.valueOf(rs.getInt("argument_order")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.DOMAIN_SCHEMA)
                            .value(rs.getString("domain_schema"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.IS_UDT)
                            .value(String.valueOf(rs.getBoolean("is_udt")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.PARAMETER_DEFAULT)
                            .value(hasColumn(rs, "parameter_default")
                                ? rs.getString("parameter_default") : null)
                            .build()))
                    .parent(loadContext.getParent())
                    .build(),
                params);
        return fillPropertiesByOwner(metadata);
    }
}
