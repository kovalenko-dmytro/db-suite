package com.abcloudz.dbsuite.loaderservice.service.loader.vendor.postgresql.metadataloader;

import com.abcloudz.dbsuite.loaderservice.service.loader.MetadataLoader;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class AbstractPostgreSqlMetadataLoader implements MetadataLoader<JdbcTemplate> {

    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDatabaseClient(JdbcTemplate databaseClient) {
        jdbcTemplate = databaseClient;
    }
}
