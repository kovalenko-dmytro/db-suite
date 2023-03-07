package com.gmail.apachdima.dbsuite.metadataservice.dto.loader;

import com.gmail.apachdima.dbsuite.metadataservice.dto.connection.ConnectionResponseDTO;
import com.gmail.apachdima.dbsuite.metadataservice.model.category.MetadataCategory;
import com.gmail.apachdima.dbsuite.metadataservice.model.metadata.Metadata;
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
