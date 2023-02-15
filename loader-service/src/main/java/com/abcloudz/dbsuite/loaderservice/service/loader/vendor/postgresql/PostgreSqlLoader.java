package com.abcloudz.dbsuite.loaderservice.service.loader.vendor.postgresql;

import com.abcloudz.dbsuite.loaderservice.dto.connection.ConnectionResponseDTO;
import com.abcloudz.dbsuite.loaderservice.model.category.MetadataCategory;
import com.abcloudz.dbsuite.loaderservice.model.metadata.Metadata;
import com.abcloudz.dbsuite.loaderservice.service.loader.MetadataLoader;
import com.abcloudz.dbsuite.loaderservice.service.loader.VendorLoader;
import com.abcloudz.dbsuite.loaderservice.service.loader.provider.DatabaseClientProvider;
import com.abcloudz.dbsuite.loaderservice.service.loader.provider.MetadataLoaderProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component("PostgreSqlLoader")
@RequiredArgsConstructor
public class PostgreSqlLoader implements VendorLoader {

    private final DatabaseClientProvider<JdbcTemplate> rdbmcDatabaseClientProvider;
    private final MetadataLoaderProvider<JdbcTemplate> postgreSqlMetadataLoaderProvider;

    @Override
    public Metadata load(ConnectionResponseDTO connection, MetadataCategory category, Metadata parent, Locale locale) {
        MetadataLoader<JdbcTemplate> metadataLoader = postgreSqlMetadataLoaderProvider.getMetadataLoader(category.getType(), locale);
        metadataLoader.setDatabaseClient(rdbmcDatabaseClientProvider.obtainClient(connection));
        Metadata metadata = metadataLoader.loadMetadata(category, parent, locale);
        metadata.setConnectionGuid(connection.getConnectionGuid());
        return metadata;
    }
}
