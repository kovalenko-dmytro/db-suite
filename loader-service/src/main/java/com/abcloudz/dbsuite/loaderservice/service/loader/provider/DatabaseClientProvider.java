package com.abcloudz.dbsuite.loaderservice.service.loader.provider;

import com.abcloudz.dbsuite.loaderservice.dto.connection.ConnectionResponseDTO;
import com.abcloudz.dbsuite.loaderservice.model.category.VendorType;
import com.abcloudz.dbsuite.loaderservice.model.databaseclient.DatabaseClient;

public interface DatabaseClientProvider {

    DatabaseClient<?> obtainClient(VendorType vendorType, ConnectionResponseDTO connection);
}
