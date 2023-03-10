package com.gmail.apachdima.dbsuite.vendorservice.service.general;

import com.gmail.apachdima.dbsuite.vendorservice.dto.vendor.VendorResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Locale;

public interface VendorService {

    Page<VendorResponseDTO> find(Pageable pageable);
    VendorResponseDTO findByGuid(String vendorGuid, Locale locale);
}
