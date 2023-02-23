package com.abcloudz.dbsuite.loaderservice.dto.loader;

import com.abcloudz.dbsuite.loaderservice.dto.connection.ConnectionResponseDTO;
import com.abcloudz.dbsuite.loaderservice.model.category.MetadataCategory;
import com.abcloudz.dbsuite.loaderservice.model.databaseclient.DatabaseClient;
import com.abcloudz.dbsuite.loaderservice.model.metadata.Metadata;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LoadContext {

    private ConnectionResponseDTO connection;
    private MetadataCategory category;
    private Metadata parent;
    private DatabaseClient<?> databaseClient;
    private String query;
}
