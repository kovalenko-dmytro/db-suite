package com.abcloudz.dbsuite.loaderservice.service.loader.provider;

import com.abcloudz.dbsuite.loaderservice.dto.connection.ConnectionResponseDTO;

public interface DatabaseClientProvider<DC> {

    DC obtainClient(ConnectionResponseDTO connection);
}
