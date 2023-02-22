package com.abcloudz.dbsuite.loaderservice.service.loader.provider;

import com.abcloudz.dbsuite.loaderservice.dto.connection.ConnectionResponseDTO;
import com.abcloudz.dbsuite.loaderservice.model.category.VendorType;
import com.abcloudz.dbsuite.loaderservice.model.databaseclient.DatabaseClient;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class DatabaseClientProviderImpl implements DatabaseClientProvider {

    private static final String RDBMS_URL_FORMAT = "jdbc:{0}://{1}:{2}/{3}?verifyServerCertificate={4}&useSSL={5}&requireSSL={6}";

    private final Map<VendorType, Map<String, DatabaseClient<?>>> databaseClients = new ConcurrentHashMap<>();

    @Override
    public DatabaseClient<?> obtainClient(VendorType vendorType, ConnectionResponseDTO connection) {
        Map<String, DatabaseClient<?>> vendorDatabaseClients = databaseClients.get(vendorType);
        if (Objects.isNull(vendorDatabaseClients)) {
            databaseClients.put(vendorType, Map.of(connection.getConnectionGuid(), addClient(vendorType, connection)));
            return databaseClients.get(vendorType).get(connection.getConnectionGuid());
        }
        DatabaseClient<?> databaseClient = vendorDatabaseClients.get(connection.getConnectionGuid());
        if (Objects.isNull(databaseClient)) {
            return vendorDatabaseClients.put(connection.getConnectionGuid(), addClient(vendorType, connection));
        }
        return  databaseClient;
    }

    private DatabaseClient<?> addClient(VendorType vendorType, ConnectionResponseDTO connection) {
        switch (vendorType) {
            case POSTGRESQL:
            case MYSQL:
                return addRDBMSClient(connection);
            default:
                throw new UnsupportedOperationException();
        }
    }

    private DatabaseClient<?> addRDBMSClient(ConnectionResponseDTO connection) {
        DataSource dataSource = DataSourceBuilder.create()
            .driverClassName(connection.getVendor().getDriver())
            .url(buildJdbcUrl(connection))
            .username(connection.getUsername())
            .password(connection.getPassword())
            .build();
        return new DatabaseClient<>(new JdbcTemplate(dataSource));
    }

    private String buildJdbcUrl(ConnectionResponseDTO connection) {
        return MessageFormat.format(
            RDBMS_URL_FORMAT,
            connection.getVendor().getJdbcName(),
            connection.getHost(),
            connection.getPort(),
            connection.getDatabase(),
            connection.getVerifyServerCertificate(),
            connection.getUseSSL(),
            connection.getRequireSSL());
    }
}
