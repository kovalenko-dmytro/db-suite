package com.abcloudz.dbsuite.vendorservice.util.mapper;

import com.abcloudz.dbsuite.vendorservice.dto.connection.ConnectionResponseDTO;
import com.abcloudz.dbsuite.vendorservice.model.connection.Connection;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ConnectionMapper {

    ConnectionResponseDTO toConnectionResponseDTO(Connection connection);
}
