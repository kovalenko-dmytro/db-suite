package com.gmail.apachdima.dbsuite.vendorservice.service.verifier.impl;

import com.gmail.apachdima.dbsuite.vendorservice.common.message.Error;
import com.gmail.apachdima.dbsuite.vendorservice.common.message.Info;
import com.gmail.apachdima.dbsuite.vendorservice.dto.connection.ConnectionVerifyResponseDTO;
import com.gmail.apachdima.dbsuite.vendorservice.model.connection.Connection;
import com.gmail.apachdima.dbsuite.vendorservice.service.verifier.ConnectionVerifier;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Objects;

@Component("RDBMSConnectionVerifier")
@RequiredArgsConstructor
public class RDBMSConnectionVerifier implements ConnectionVerifier {

    private static final String URL_FORMAT = "jdbc:{0}://{1}:{2}/{3}?verifyServerCertificate={4}&useSSL={5}&requireSSL={6}";

    private final MessageSource messageSource;

    @Override
    public ConnectionVerifyResponseDTO verify(Connection connection, Locale locale) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(buildDataSource(connection));
        try {
            boolean connectionValid = isConnectionValid(jdbcTemplate);
            return ConnectionVerifyResponseDTO.builder()
                .verified(connectionValid)
                .message(getMessage(connection, locale, connectionValid))
                .build();
        } catch (SQLException e) {
            return ConnectionVerifyResponseDTO.builder().isError(true).message(e.getMessage()).build();
        }
    }

    private String getMessage(Connection connection, Locale locale, boolean connectionValid) {
        String connectionName = connection.getConnectionName();
        String vendorDisplayName = connection.getVendor().getDisplayName();
        return connectionValid
            ? messageSource.getMessage(Info.CONNECTION_VERIFIED.getKey(), new Object[]{connectionName}, locale)
            : messageSource.getMessage(Error.VENDOR_CONNECTION_UNVERIFIED.getKey(), new Object[]{connectionName, vendorDisplayName}, locale);
    }

    private DataSource buildDataSource(Connection connection) {
        HikariConfig config = new HikariConfig();
        config.setMaximumPoolSize(30);
        config.setConnectionTimeout(300000);
        config.setConnectionTimeout(120000);
        config.setLeakDetectionThreshold(300000);
        config.setJdbcUrl(buildUrlConnection(connection));
        config.setUsername(connection.getUsername());
        config.setPassword(connection.getPassword());
        config.setDriverClassName(connection.getVendor().getDriver());
        return new HikariDataSource(config);
    }

    private String buildUrlConnection(Connection connection) {
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

    private boolean isConnectionValid(JdbcTemplate jdbcTemplate) throws SQLException {
        DataSource dataSource = jdbcTemplate.getDataSource();
        return Objects.nonNull(dataSource) && dataSource.getConnection().isValid(5);
    }
}
