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

@Component("UserDefinedTypePostgreSqlLoader")
public class UserDefinedTypePostgreSqlLoader implements MetadataLoader {

    @Override
    public List<Metadata> loadMetadata(LoadContext loadContext) {
        Object[] params = new Object[]{
            loadContext.getParent().extractProperty(MetadataType.DATABASE, MetadataPropertyName.NAME),
            loadContext.getParent().extractProperty(MetadataType.SCHEMA, MetadataPropertyName.NAME)};

        List<Metadata> metadata = ((JdbcTemplate) loadContext.getDatabaseClient().getClient())
            .query(loadContext.getQuery(), (rs, rowNum) ->
                Metadata.builder()
                    .connectionGuid(loadContext.getConnection().getConnectionGuid())
                    .type(MetadataType.USER_DEFINED_TYPE)
                    .category(loadContext.getCategory())
                    .properties(List.of(
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.NAME)
                            .value(rs.getString("name"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.TYPE_NAME)
                            .value(rs.getString("type_name"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.UDT_NAME)
                            .value(rs.getString("udt_name"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.UDT_SCHEMA)
                            .value(rs.getString("udt_schema"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.CHARACTER_MAXIMUM_LENGTH)
                            .value(rs.getBigDecimal("character_maximum_length") == null
                                ? null : String.valueOf(rs.getBigDecimal("character_maximum_length")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.IS_UDT)
                            .value(String.valueOf(rs.getBoolean("is_udt")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.DOMAIN_SCHEMA)
                            .value(rs.getString("domain_schema"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.NUMERIC_PRECISION)
                            .value(rs.getBigDecimal("numeric_precision") == null
                                ? null : String.valueOf(rs.getBigDecimal("numeric_precision")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.NUMERIC_SCALE)
                            .value(rs.getBigDecimal("numeric_scale") == null
                                ? null : String.valueOf(rs.getBigDecimal("numeric_scale")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.ALLOW_NULLS)
                            .value(String.valueOf(rs.getBoolean("allow_nulls")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.IS_DOMAIN)
                            .value(String.valueOf(rs.getBoolean("is_domain")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.IS_TYPE_ENUM)
                            .value(String.valueOf(rs.getBoolean("is_type_enum")))
                            .build()))
                    .parent(loadContext.getParent())
                    .build(),
                params);
        return fillPropertiesByOwner(metadata);
    }
}
