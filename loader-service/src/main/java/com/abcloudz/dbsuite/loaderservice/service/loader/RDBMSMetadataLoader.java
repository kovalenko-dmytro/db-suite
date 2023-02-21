package com.abcloudz.dbsuite.loaderservice.service.loader;

import lombok.Getter;
import org.springframework.jdbc.core.JdbcTemplate;

@Getter
public abstract class RDBMSMetadataLoader implements MetadataLoader<JdbcTemplate> {

    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDatabaseClient(JdbcTemplate databaseClient) {
        jdbcTemplate = databaseClient;
    }
}
