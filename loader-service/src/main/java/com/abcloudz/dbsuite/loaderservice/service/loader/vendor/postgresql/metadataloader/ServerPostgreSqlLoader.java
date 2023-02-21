package com.abcloudz.dbsuite.loaderservice.service.loader.vendor.postgresql.metadataloader;

import com.abcloudz.dbsuite.loaderservice.model.metadata.Metadata;
import com.abcloudz.dbsuite.loaderservice.model.metadata.MetadataProperty;
import com.abcloudz.dbsuite.loaderservice.model.metadata.MetadataPropertyName;
import com.abcloudz.dbsuite.loaderservice.model.metadata.MetadataType;
import com.abcloudz.dbsuite.loaderservice.service.loader.RDBMSMetadataLoader;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("ServerPostgreSqlLoader")
public class ServerPostgreSqlLoader extends RDBMSMetadataLoader {

    private static final Pattern SERVER_NAME_PATTERN = Pattern.compile("//(.*?)/");

    @Override
    public List<Metadata> loadMetadata(String query, Metadata parent, Locale locale) {
        return getJdbcTemplate()
            .query(query, (rs, rowNum) ->
                Metadata.builder()
                    .type(MetadataType.SERVER)
                    .properties(List.of(
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.NAME)
                            .value(getServerName(rs.getStatement().getConnection().getMetaData().getURL()))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.VERSION)
                            .value(rs.getString("version"))
                            .build(),
                        MetadataProperty.builder()
                            .name(MetadataPropertyName.UUID)
                            .value(rs.getString("uuid"))
                            .build()))
                    .build());
    }

    private String getServerName(String url) {
        Matcher matcher = SERVER_NAME_PATTERN.matcher(url);
        return matcher.find() ? matcher.group(1) : url;
    }
}
