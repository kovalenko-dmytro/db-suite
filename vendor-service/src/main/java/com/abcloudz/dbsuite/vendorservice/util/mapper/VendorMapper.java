package com.abcloudz.dbsuite.vendorservice.util.mapper;

import com.abcloudz.dbsuite.vendorservice.dto.vendor.VendorResponseDTO;
import com.abcloudz.dbsuite.vendorservice.model.vendor.Vendor;
import com.abcloudz.dbsuite.vendorservice.model.vendor.VendorType;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.Objects;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface VendorMapper {

    VendorResponseDTO toVendorResponseDTO(Vendor vendor);

    default String vendorTypeToString(VendorType type) {
        return (Objects.isNull(type)) ? null : type.getVendorType();
    }
}
