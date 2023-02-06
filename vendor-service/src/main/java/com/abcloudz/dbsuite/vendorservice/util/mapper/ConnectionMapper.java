package com.abcloudz.dbsuite.vendorservice.util.mapper;

import com.abcloudz.dbsuite.vendorservice.dto.connection.ConnectionResponseDTO;
import com.abcloudz.dbsuite.vendorservice.model.connection.Connection;
import com.abcloudz.dbsuite.vendorservice.model.vendor.VendorType;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.Objects;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ConnectionMapper {

    ConnectionResponseDTO toConnectionResponseDTO(Connection connection);

    default String vendorTypeToString(VendorType type) {
        return (Objects.isNull(type)) ? null : type.getVendorType();
    }
}
