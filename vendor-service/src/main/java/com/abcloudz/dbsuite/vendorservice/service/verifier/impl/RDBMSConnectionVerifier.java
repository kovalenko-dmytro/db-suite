package com.abcloudz.dbsuite.vendorservice.service.verifier.impl;

import com.abcloudz.dbsuite.vendorservice.dto.connection.ConnectionVerifyResponseDTO;
import com.abcloudz.dbsuite.vendorservice.model.connection.Connection;
import com.abcloudz.dbsuite.vendorservice.service.verifier.ConnectionVerifier;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Objects;

@Component("RDBMSConnectionVerifier")
public class RDBMSConnectionVerifier implements ConnectionVerifier {

    private static final String URL_FORMAT = "jdbc:{0}://{1}:{2}/{3}?verifyServerCertificate={4}&useSSL={5}&requireSSL={6}";

    @Override
    public ConnectionVerifyResponseDTO verify(Connection connection) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(buildDataSource(connection));
        try {
            boolean connectionValid = isConnectionValid(jdbcTemplate);
            String message = connectionValid ? "Success" : "Check connection parameters";
            return ConnectionVerifyResponseDTO.builder().verified(connectionValid).message(message).build();
        } catch (SQLException e) {
            return ConnectionVerifyResponseDTO.builder().isError(true).message(e.getMessage()).build();
        }
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
