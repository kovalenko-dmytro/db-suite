package com.abcloudz.dbsuite.vendorservice.service.impl;

import com.abcloudz.dbsuite.vendorservice.dto.connection.ConnectionCreateRequestDTO;
import com.abcloudz.dbsuite.vendorservice.dto.connection.ConnectionResponseDTO;
import com.abcloudz.dbsuite.vendorservice.dto.connection.ConnectionUpdateRequestDTO;
import com.abcloudz.dbsuite.vendorservice.repository.ConnectionRepository;
import com.abcloudz.dbsuite.vendorservice.service.ConnectionService;
import com.abcloudz.dbsuite.vendorservice.util.mapper.ConnectionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ConnectionServiceImpl implements ConnectionService {

    private final ConnectionRepository connectionRepository;
    private final MessageSource messageSource;
    private final ConnectionMapper connectionMapper;

    @Override
    public Page<ConnectionResponseDTO> find(String vendorGuid, Pageable pageable) {
        return null;
    }

    @Override
    public ConnectionResponseDTO findByGuid(String vendorGuid, String connectionGuid, Locale locale) {
        return null;
    }

    @Override
    public ConnectionResponseDTO create(String vendorGuid, ConnectionCreateRequestDTO request) {
        return null;
    }

    @Override
    public ConnectionResponseDTO update(String connectionGuid, ConnectionUpdateRequestDTO request, Locale locale) {
        return null;
    }

    @Override
    public void validate(String connectionGuid, Locale locale) {

    }

    @Override
    public void delete(String connectionGuid, Locale locale) {

    }
}
