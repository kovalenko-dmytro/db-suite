package com.abcloudz.dbsuite.vendorservice.service;

import com.abcloudz.dbsuite.vendorservice.dto.connection.ConnectionCreateRequestDTO;
import com.abcloudz.dbsuite.vendorservice.dto.connection.ConnectionResponseDTO;
import com.abcloudz.dbsuite.vendorservice.dto.connection.ConnectionUpdateRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Locale;

public interface ConnectionService {

    Page<ConnectionResponseDTO> find(String vendorGuid, Pageable pageable);
    ConnectionResponseDTO findByGuid(String connectionGuid, Locale locale);
    ConnectionResponseDTO create(String vendorGuid, ConnectionCreateRequestDTO request, Locale locale);
    ConnectionResponseDTO update(String connectionGuid, ConnectionUpdateRequestDTO request, Locale locale);
    void validate(String connectionGuid, Locale locale);
    void delete(String connectionGuid, Locale locale);
}
