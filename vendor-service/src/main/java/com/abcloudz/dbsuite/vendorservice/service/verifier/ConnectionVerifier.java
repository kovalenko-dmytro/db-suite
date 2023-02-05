package com.abcloudz.dbsuite.vendorservice.service.verifier;

import com.abcloudz.dbsuite.vendorservice.dto.connection.ConnectionVerifyResponseDTO;
import com.abcloudz.dbsuite.vendorservice.model.connection.Connection;

public interface ConnectionVerifier {

    ConnectionVerifyResponseDTO verify(Connection connection);
}
