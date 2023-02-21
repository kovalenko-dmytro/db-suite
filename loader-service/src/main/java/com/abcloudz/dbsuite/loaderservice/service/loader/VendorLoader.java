package com.abcloudz.dbsuite.loaderservice.service.loader;

import com.abcloudz.dbsuite.loaderservice.dto.connection.ConnectionResponseDTO;
import com.abcloudz.dbsuite.loaderservice.exception.LoaderServiceApplicationException;
import com.abcloudz.dbsuite.loaderservice.model.category.MetadataCategory;
import com.abcloudz.dbsuite.loaderservice.model.metadata.Metadata;
import com.abcloudz.dbsuite.loaderservice.model.version.Version;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

public interface VendorLoader {

    List<Metadata> load(ConnectionResponseDTO connection, MetadataCategory category, Metadata parent, Locale locale);

    default Version obtainRDBMSServerVersion(JdbcTemplate databaseClient) {
        try (Connection connection = databaseClient.getDataSource().getConnection()) {
            return new Version(
                connection.getMetaData().getDatabaseMajorVersion(),
                connection.getMetaData().getDatabaseMinorVersion());
        } catch (SQLException e) {
            throw new LoaderServiceApplicationException(e.getMessage());
        }
    }
}
