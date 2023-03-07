package com.gmail.apachdima.dbsuite.vendorservice.util.mapper;

import com.gmail.apachdima.dbsuite.vendorservice.dto.connection.ConnectionResponseDTO;
import com.gmail.apachdima.dbsuite.vendorservice.model.connection.Connection;
import com.gmail.apachdima.dbsuite.vendorservice.model.vendor.VendorType;
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
