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

@Component("ColumnPostgreSqlLoader")
public class ColumnPostgreSqlLoader implements MetadataLoader {

    @Override
    public List<Metadata> loadMetadata(LoadContext loadContext) {
        Object[] params = new Object[]{
            loadContext.getParent().extractProperty(MetadataType.SCHEMA, MetadataPropertyName.NAME),
            loadContext.getParent().extractProperty(loadContext.getParent().getType(), MetadataPropertyName.NAME)};

        List<Metadata> metadata = ((JdbcTemplate) loadContext.getDatabaseClient().getClient())
            .query(loadContext.getQuery(), (rs, rowNum) ->
                Metadata.builder()
                    .connectionGuid(loadContext.getConnection().getConnectionGuid())
                    .type(MetadataType.COLUMN)
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
                            .name(MetadataPropertyName.TYPE_NAME_ALIAS)
                            .value(rs.getString("type_name_alias"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.CHARACTER_MAXIMUM_LENGTH)
                            .value(rs.getBigDecimal("character_maximum_length") == null
                                ? null : String.valueOf(rs.getBigDecimal("character_maximum_length")))
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
                            .name(MetadataPropertyName.IS_IDENTITY)
                            .value(String.valueOf(rs.getBoolean("is_identity")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.IS_NULLABLE)
                            .value(String.valueOf(rs.getBoolean("is_nullable")))
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
                            .name(MetadataPropertyName.UDT_SCHEMA)
                            .value(rs.getString("udt_schema"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.IS_GENERATED)
                            .value(rs.getString("is_generated"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.IDENTITY_OPTION)
                            .value(hasColumn(rs, "identity_option")
                                ? rs.getString("identity_option") : null)
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.COLUMN_DEFAULT)
                            .value(rs.getString("column_default"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.ORDINAL_POSITION)
                            .value(String.valueOf(rs.getInt("ordinal_position")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.COLLATION_NAME)
                            .value(rs.getString("collation_name"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.MINIMUM_VALUE)
                            .value(rs.getBigDecimal("minimum_value") == null
                                ? null : String.valueOf(rs.getBigDecimal("minimum_value")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.INCREMENT)
                            .value(rs.getBigDecimal("increment") == null
                                ? null : String.valueOf(rs.getBigDecimal("increment")))
                            .build()))
                    .parent(loadContext.getParent())
                    .build(),
                params);
        return fillPropertiesByOwner(metadata);
    }
}
