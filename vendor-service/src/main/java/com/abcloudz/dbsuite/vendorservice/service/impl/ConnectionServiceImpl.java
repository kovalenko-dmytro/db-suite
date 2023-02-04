package com.abcloudz.dbsuite.vendorservice.service.impl;

import com.abcloudz.dbsuite.vendorservice.common.Entity;
import com.abcloudz.dbsuite.vendorservice.common.message.Error;
import com.abcloudz.dbsuite.vendorservice.dto.connection.ConnectionCreateRequestDTO;
import com.abcloudz.dbsuite.vendorservice.dto.connection.ConnectionResponseDTO;
import com.abcloudz.dbsuite.vendorservice.dto.connection.ConnectionUpdateRequestDTO;
import com.abcloudz.dbsuite.vendorservice.exception.EntityNotFoundException;
import com.abcloudz.dbsuite.vendorservice.model.connection.Connection;
import com.abcloudz.dbsuite.vendorservice.model.vendor.Vendor;
import com.abcloudz.dbsuite.vendorservice.repository.ConnectionRepository;
import com.abcloudz.dbsuite.vendorservice.repository.VendorRepository;
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

    private final VendorRepository vendorRepository;
    private final ConnectionRepository connectionRepository;
    private final MessageSource messageSource;
    private final ConnectionMapper connectionMapper;

    @Override
    public Page<ConnectionResponseDTO> find(String vendorGuid, Pageable pageable) {
        return connectionRepository
            .findByVendor_vendorGuid(vendorGuid, pageable)
            .map(connectionMapper::toConnectionResponseDTO);
    }

    @Override
    public ConnectionResponseDTO findByGuid(String connectionGuid, Locale locale) {
        Connection connection = getConnectionByGuid(connectionGuid, locale);
        return connectionMapper.toConnectionResponseDTO(connection);
    }

    @Override
    public ConnectionResponseDTO create(String vendorGuid, ConnectionCreateRequestDTO request, Locale locale) {
        Vendor vendor = getVendorByGuid(vendorGuid, locale);
        Connection connection = Connection.builder()
            .vendor(vendor)
            .connectionName(request.getConnectionName())
            .host(request.getHost())
            .port(request.getPort())
            .database(request.getDatabase())
            .username(request.getUsername())
            .password(request.getPassword())
            .verifyServerCertificate(request.getVerifyServerCertificate())
            .useSSL(request.getUseSSL())
            .requireSSL(request.getRequireSSL())
            .serverVersion(null)
            .verified(false)
            .build();
        return connectionMapper.toConnectionResponseDTO(connectionRepository.save(connection));
    }

    @Override
    public ConnectionResponseDTO update(String connectionGuid, ConnectionUpdateRequestDTO request, Locale locale) {
        Connection connection = getConnectionByGuid(connectionGuid, locale);
        connection.setConnectionName(request.getConnectionName());
        connection.setHost(request.getHost());
        connection.setPort(request.getPort());
        connection.setDatabase(request.getDatabase());
        connection.setUsername(request.getUsername());
        connection.setPassword(request.getPassword());
        connection.setVerifyServerCertificate(request.getVerifyServerCertificate());
        connection.setUseSSL(request.getUseSSL());
        connection.setRequireSSL(request.getRequireSSL());
        return connectionMapper.toConnectionResponseDTO(connectionRepository.save(connection));
    }

    @Override
    public void validate(String connectionGuid, Locale locale) {

    }

    @Override
    public void delete(String connectionGuid, Locale locale) {
        Connection connection = getConnectionByGuid(connectionGuid, locale);
        connectionRepository.delete(connection);
    }

    private Vendor getVendorByGuid(String vendorGuid, Locale locale) {
        Object[] params = new Object[]{
            Entity.CONNECTION.getName(),
            Entity.ConnectionField.VENDOR_GUID.getFieldName(),
            vendorGuid};
        return vendorRepository
            .findById(vendorGuid)
            .orElseThrow(() ->
                new EntityNotFoundException(messageSource.getMessage(Error.ENTITY_NOT_FOUND.getKey(), params, locale)));
    }

    private Connection getConnectionByGuid(String connectionGuid, Locale locale) {
        Object[] params = new Object[]{
            Entity.CONNECTION.getName(),
            Entity.ConnectionField.CONNECTION_GUID.getFieldName(),
            connectionGuid};
        return connectionRepository.findById(connectionGuid)
            .orElseThrow(() ->
                new EntityNotFoundException(messageSource.getMessage(Error.ENTITY_NOT_FOUND.getKey(), params, locale)));
    }
}
