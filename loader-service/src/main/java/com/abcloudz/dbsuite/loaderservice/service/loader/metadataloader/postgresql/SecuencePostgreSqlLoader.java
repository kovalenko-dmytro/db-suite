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

@Component("SecuencePostgreSqlLoader")
public class SecuencePostgreSqlLoader implements MetadataLoader {

    @Override
    public List<Metadata> loadMetadata(LoadContext loadContext) {
        Object[] params = new Object[]{
            loadContext.getParent().extractProperty(MetadataType.DATABASE, MetadataPropertyName.NAME),
            loadContext.getParent().extractProperty(MetadataType.SCHEMA, MetadataPropertyName.NAME)};

        List<Metadata> metadata = ((JdbcTemplate) loadContext.getDatabaseClient().getClient())
            .query(loadContext.getQuery(), (rs, rowNum) ->
                Metadata.builder()
                    .connectionGuid(loadContext.getConnection().getConnectionGuid())
                    .type(MetadataType.SEQUENCE)
                    .category(loadContext.getCategory())
                    .properties(List.of(
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.NAME)
                            .value(rs.getString("name"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.IS_UDT)
                            .value(String.valueOf(rs.getBoolean("is_udt")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.TYPE_NAME)
                            .value(rs.getString("type_name"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.START_VALUE)
                            .value(String.valueOf(rs.getBigDecimal("start_value")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.MINIMUM_VALUE)
                            .value(String.valueOf(rs.getBigDecimal("minimum_value")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.CHARACTER_MAXIMUM_LENGTH)
                            .value(String.valueOf(rs.getBigDecimal("character_maximum_length")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.INCREMENT)
                            .value(String.valueOf(rs.getBigDecimal("increment")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.NUMERIC_PRECISION)
                            .value(String.valueOf(rs.getBigDecimal("numeric_precision")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.NUMERIC_SCALE)
                            .value(String.valueOf(rs.getBigDecimal("numeric_scale")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.CYCLE_OPTION)
                            .value(String.valueOf(rs.getBoolean("cycle_option")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.CACHE_FLAG)
                            .value(String.valueOf(rs.getBoolean("cache_flag")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.CACHE_SIZE)
                            .value(String.valueOf(rs.getBigDecimal("cache_size")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.LAST_VALUE)
                            .value(String.valueOf(rs.getBigDecimal("last_value")))
                            .build()
                        ))
                    .parent(loadContext.getParent())
                    .build(),
                params);
        return fillPropertiesByOwner(metadata);
    }
}
