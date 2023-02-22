package com.abcloudz.dbsuite.loaderservice.service.loader.vendor.postgresql.metadataloader;

import com.abcloudz.dbsuite.loaderservice.model.metadata.Metadata;
import com.abcloudz.dbsuite.loaderservice.model.metadata.MetadataProperty;
import com.abcloudz.dbsuite.loaderservice.model.metadata.MetadataPropertyName;
import com.abcloudz.dbsuite.loaderservice.model.metadata.MetadataType;
import com.abcloudz.dbsuite.loaderservice.service.loader.RDBMSMetadataLoader;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component("ExtensionPostgreSqlLoader")
public class ExtensionPostgreSqlLoader extends RDBMSMetadataLoader {

    @Override
    public List<Metadata> loadMetadata(String query, Metadata parent, Locale locale) {
        Object[] params = new Object[]{parent.extractProperty(MetadataType.DATABASE, MetadataPropertyName.NAME)};
        return getJdbcTemplate()
            .query(query, (rs, rowNum) ->
                Metadata.builder()
                    .type(MetadataType.EXTENSION)
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
                    .build(),
                params);
    }
}
