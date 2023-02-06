package com.abcloudz.dbsuite.vendorservice.service.verifier;

import com.abcloudz.dbsuite.vendorservice.dto.connection.ConnectionVerifyResponseDTO;
import com.abcloudz.dbsuite.vendorservice.model.connection.Connection;

import java.util.Locale;

public interface ConnectionVerifier {

    ConnectionVerifyResponseDTO verify(Connection connection, Locale locale);
}
