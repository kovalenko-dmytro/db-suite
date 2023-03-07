package com.gmail.apachdima.dbsuite.vendorservice.util.mapper;

import com.gmail.apachdima.dbsuite.vendorservice.dto.vendor.VendorResponseDTO;
import com.gmail.apachdima.dbsuite.vendorservice.model.vendor.Vendor;
import com.gmail.apachdima.dbsuite.vendorservice.model.vendor.VendorType;
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
