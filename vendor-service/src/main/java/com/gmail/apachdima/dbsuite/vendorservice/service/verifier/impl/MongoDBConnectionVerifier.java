package com.gmail.apachdima.dbsuite.vendorservice.service.verifier.impl;

import com.gmail.apachdima.dbsuite.vendorservice.dto.connection.ConnectionVerifyResponseDTO;
import com.gmail.apachdima.dbsuite.vendorservice.model.connection.Connection;
import com.gmail.apachdima.dbsuite.vendorservice.service.verifier.ConnectionVerifier;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component("MongoDBConnectionVerifier")
public class MongoDBConnectionVerifier implements ConnectionVerifier {

    @Override
    public ConnectionVerifyResponseDTO verify(Connection connection, Locale locale) {
        throw new UnsupportedOperationException();
    }
}
