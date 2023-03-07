package com.gmail.apachdima.dbsuite.metadataservice.service.loader.metadataloader.postgresql;

import com.gmail.apachdima.dbsuite.metadataservice.dto.loader.LoadContext;
import com.gmail.apachdima.dbsuite.metadataservice.model.category.MetadataCategoryType;
import com.gmail.apachdima.dbsuite.metadataservice.model.metadata.Metadata;
import com.gmail.apachdima.dbsuite.metadataservice.model.metadata.MetadataProperty;
import com.gmail.apachdima.dbsuite.metadataservice.model.metadata.MetadataPropertyName;
import com.gmail.apachdima.dbsuite.metadataservice.model.metadata.MetadataType;
import com.gmail.apachdima.dbsuite.metadataservice.service.loader.metadataloader.MetadataLoader;
import org.springframework.data.util.Pair;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("ConstraintPostgreSqlLoader")
public class ConstraintPostgreSqlLoader implements MetadataLoader {

    @Override
    public List<Metadata> loadMetadata(LoadContext loadContext) {
        Pair<String, MetadataType> constraintType = obtainConstraintType(loadContext);
        Object[] params = new Object[]{
            constraintType.getFirst(),
            loadContext.getParent().extractProperty(MetadataType.SCHEMA, MetadataPropertyName.NAME),
            loadContext.getParent().extractProperty(loadContext.getParent().getType(), MetadataPropertyName.NAME)};

        List<Metadata> metadata = ((JdbcTemplate) loadContext.getDatabaseClient().getClient())
            .query(loadContext.getQuery(), (rs, rowNum) ->
                Metadata.builder()
                    .connectionGuid(loadContext.getConnection().getConnectionGuid())
                    .type(constraintType.getSecond())
                    .category(loadContext.getCategory())
                    .properties(List.of(
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.NAME)
                            .value(rs.getString("name"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.REFERENCED_DATABASE)
                            .value(rs.getString("referenced_database"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.TABLE_SCHEMA)
                            .value(rs.getString("table_schema"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.TABLE_NAME)
                            .value(rs.getString("table_name"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.CONSTRAINT_SCHEMA)
                            .value(rs.getString("constraint_schema"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.CONSTRAINT_TYPE)
                            .value(rs.getString("constraint_type"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.IS_DEFERRABLE)
                            .value(String.valueOf(rs.getBoolean("is_deferrable")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.IS_INITIALLY_DEFERRED)
                            .value(String.valueOf(rs.getBoolean("is_initially_deferred")))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.CONSTRAINT_TYPE_DESC)
                            .value(rs.getString("constraint_type_desc"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.CHECK_CLAUSE)
                            .value(rs.getString("check_clause"))
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
                            .name(MetadataPropertyName.REFERENCED_CONSTRAINT_SCHEMA)
                            .value(rs.getString("referenced_constraint_schema"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.REFERENCED_CONSTRAINT_NAME)
                            .value(rs.getString("referenced_constraint_name"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.MATCH_OPTION)
                            .value(rs.getString("match_option"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.UPDATE_RULE)
                            .value(rs.getString("update_rule"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.DELETE_RULE)
                            .value(rs.getString("delete_rule"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.IS_CLUSTERED)
                            .value(String.valueOf(rs.getBoolean("is_clustered")))
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

    private Pair<String, MetadataType> obtainConstraintType(LoadContext loadContext) {
        MetadataCategoryType type = loadContext.getCategory().getType();
        switch (type) {
            case PRIMARY_KEYS:
                return Pair.of("PRIMARY KEY", MetadataType.PRIMARY_KEY);
            case FOREIGN_KEYS:
                return Pair.of("FOREIGN KEY", MetadataType.FOREIGN_KEY);
            case UNIQUE_KEYS:
                return Pair.of("UNIQUE KEY", MetadataType.UNIQUE_KEY);
            default:
                throw new IllegalArgumentException("Constraint type for category <" + type.getType() + "> not found");
        }
    }
}
