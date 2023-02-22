package com.abcloudz.dbsuite.loaderservice.service.loader;

import com.abcloudz.dbsuite.loaderservice.model.databaseclient.DatabaseClient;
import lombok.Getter;
import org.springframework.jdbc.core.JdbcTemplate;

@Getter
public abstract class RDBMSMetadataLoader implements MetadataLoader {

    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDatabaseClient(DatabaseClient<?> databaseClient) {
        jdbcTemplate = (JdbcTemplate) databaseClient.getClient();
    }
}
