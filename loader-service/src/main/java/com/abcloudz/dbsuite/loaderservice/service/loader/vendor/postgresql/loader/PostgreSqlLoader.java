package com.abcloudz.dbsuite.loaderservice.service.loader.vendor.postgresql.loader;

import com.abcloudz.dbsuite.loaderservice.dto.connection.ConnectionResponseDTO;
import com.abcloudz.dbsuite.loaderservice.model.category.MetadataCategory;
import com.abcloudz.dbsuite.loaderservice.model.category.VendorType;
import com.abcloudz.dbsuite.loaderservice.model.metadata.Metadata;
import com.abcloudz.dbsuite.loaderservice.model.version.Version;
import com.abcloudz.dbsuite.loaderservice.service.loader.MetadataLoader;
import com.abcloudz.dbsuite.loaderservice.service.loader.VendorLoader;
import com.abcloudz.dbsuite.loaderservice.service.loader.provider.DatabaseClientProvider;
import com.abcloudz.dbsuite.loaderservice.service.loader.provider.MetadataLoaderProvider;
import com.abcloudz.dbsuite.loaderservice.service.loader.query.QueryHolder;
import com.abcloudz.dbsuite.loaderservice.service.loader.query.QueryKey;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Component("PostgreSqlLoader")
@RequiredArgsConstructor
public class PostgreSqlLoader implements VendorLoader {

    private final DatabaseClientProvider<JdbcTemplate> rdbmcDatabaseClientProvider;
    private final MetadataLoaderProvider<JdbcTemplate> postgreSqlMetadataLoaderProvider;
    private final QueryHolder queryHolder;

    @Override
    public List<Metadata> load(ConnectionResponseDTO connection, MetadataCategory category, Metadata parent, Locale locale) {
        MetadataLoader<JdbcTemplate> metadataLoader = postgreSqlMetadataLoaderProvider.getMetadataLoader(category.getType(), locale);
        JdbcTemplate databaseClient = rdbmcDatabaseClientProvider.obtainClient(connection);
        metadataLoader.setDatabaseClient(databaseClient);

        VendorType vendorType = VendorType.getType(connection.getVendor().getType());
        Version serverVersion = Objects.isNull(parent) ? obtainRDBMSServerVersion(databaseClient) : parent.getServerVersion();
        String query = queryHolder.getQuery(vendorType, serverVersion, QueryKey.obtainQueryKey(category.getType()), locale);

        List<Metadata> metadataObjects = metadataLoader.loadMetadata(query, parent, locale);
        metadataObjects.forEach(metadata -> {
            metadata.setConnectionGuid(connection.getConnectionGuid());
            metadata.setCategory(category);
            metadata.setParent(parent);
            metadata.getProperties().forEach(metadataProperty -> metadataProperty.setMetadata(metadata));
            });

        return metadataObjects;
    }
}
