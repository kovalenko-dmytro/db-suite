package com.gmail.apachdima.dbsuite.vendorservice.service.verifier;

import com.gmail.apachdima.dbsuite.vendorservice.dto.connection.ConnectionVerifyResponseDTO;
import com.gmail.apachdima.dbsuite.vendorservice.model.connection.Connection;

import java.util.Locale;

public interface ConnectionVerifier {

    ConnectionVerifyResponseDTO verify(Connection connection, Locale locale);
}
