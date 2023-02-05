package com.abcloudz.dbsuite.vendorservice.service.verifier.impl;

import com.abcloudz.dbsuite.vendorservice.dto.connection.ConnectionVerifyResponseDTO;
import com.abcloudz.dbsuite.vendorservice.model.connection.Connection;
import com.abcloudz.dbsuite.vendorservice.service.verifier.ConnectionVerifier;

public class MongoDBConnectionVerifier implements ConnectionVerifier {

    @Override
    public ConnectionVerifyResponseDTO verify(Connection connection) {
        throw new UnsupportedOperationException();
    }
}
