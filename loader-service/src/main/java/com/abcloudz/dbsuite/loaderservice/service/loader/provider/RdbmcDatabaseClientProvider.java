package com.abcloudz.dbsuite.loaderservice.service.loader.provider;

import com.abcloudz.dbsuite.loaderservice.dto.connection.ConnectionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.text.MessageFormat;

@Component
@RequiredArgsConstructor
public class RdbmcDatabaseClientProvider implements DatabaseClientProvider<JdbcTemplate> {

    private static final String URL_FORMAT = "jdbc:{0}://{1}:{2}/{3}?verifyServerCertificate={4}&useSSL={5}&requireSSL={6}";

    @Override
    public JdbcTemplate obtainClient(ConnectionResponseDTO connection) {
        DataSource dataSource = DataSourceBuilder.create()
            .driverClassName(connection.getVendor().getDriver())
            .url(buildJdbcUrl(connection))
            .username(connection.getUsername())
            .password(connection.getPassword())
            .build();

        return new JdbcTemplate(dataSource);
    }

    private String buildJdbcUrl(ConnectionResponseDTO connection) {
        return MessageFormat.format(
            URL_FORMAT,
            connection.getVendor().getJdbcName(),
            connection.getHost(),
            connection.getPort(),
            connection.getDatabase(),
            connection.getVerifyServerCertificate(),
            connection.getUseSSL(),
            connection.getRequireSSL());
    }
}
