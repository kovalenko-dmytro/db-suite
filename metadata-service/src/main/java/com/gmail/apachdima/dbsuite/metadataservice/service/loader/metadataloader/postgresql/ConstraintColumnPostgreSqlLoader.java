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

@Component("ConstraintColumnPostgreSqlLoader")
public class ConstraintColumnPostgreSqlLoader implements MetadataLoader {

    @Override
    public List<Metadata> loadMetadata(LoadContext loadContext) {
        Object[] params = new Object[]{
            loadContext.getParent().extractProperty(MetadataType.SCHEMA, MetadataPropertyName.NAME),
            loadContext.getParent().extractProperty(MetadataType.TABLE, MetadataPropertyName.NAME),
            loadContext.getParent().extractProperty(loadContext.getParent().getType(), MetadataPropertyName.NAME)};

        List<Metadata> metadata = ((JdbcTemplate) loadContext.getDatabaseClient().getClient())
            .query(loadContext.getQuery(), (rs, rowNum) ->
                Metadata.builder()
                    .connectionGuid(loadContext.getConnection().getConnectionGuid())
                    .type(MetadataType.CONSTRAINT_COLUMN)
                    .category(loadContext.getCategory())
                    .properties(List.of(
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.NAME)
                            .value(rs.getString("name"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.ORDINAL_POSITION)
                            .value(String.valueOf(rs.getInt("ordinal_position")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.POSITION_IN_UNIQUE_CONSTRAINT)
                            .value(String.valueOf(rs.getInt("position_in_unique_constraint")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.REFERENCED_TABLE_SCHEMA)
                            .value(rs.getString("referenced_table_schema"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.REFERENCED_TABLE_NAME)
                            .value(rs.getString("referenced_table_name"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.COLUMN_IN_UNIQUE_CONSTRAINT)
                            .value(rs.getString("column_in_unique_constraint"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.CONSTRAINT_TYPE_DESC)
                            .value(rs.getString("constraint_type_desc"))
                            .build()))
                    .parent(loadContext.getParent())
                    .build(),
                params);
        return fillPropertiesByOwner(metadata);
    }
}
