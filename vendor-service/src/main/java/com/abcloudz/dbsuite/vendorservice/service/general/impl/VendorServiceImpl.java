package com.abcloudz.dbsuite.vendorservice.service.general.impl;

import com.abcloudz.dbsuite.vendorservice.common.Entity;
import com.abcloudz.dbsuite.vendorservice.common.message.Error;
import com.abcloudz.dbsuite.vendorservice.dto.vendor.VendorResponseDTO;
import com.abcloudz.dbsuite.vendorservice.exception.EntityNotFoundException;
import com.abcloudz.dbsuite.vendorservice.model.vendor.Vendor;
import com.abcloudz.dbsuite.vendorservice.repository.VendorRepository;
import com.abcloudz.dbsuite.vendorservice.service.general.VendorService;
import com.abcloudz.dbsuite.vendorservice.util.mapper.VendorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final MessageSource messageSource;
    private final VendorMapper vendorMapper;

    @Override
    public Page<VendorResponseDTO> find(Pageable pageable) {
        return vendorRepository.findAll(pageable).map(vendorMapper::toVendorResponseDTO);
    }

    @Override
    public VendorResponseDTO findByGuid(String vendorGuid, Locale locale) {
        return vendorMapper.toVendorResponseDTO(getByGuid(vendorGuid, locale));
    }

    private Vendor getByGuid(String vendorGuid, Locale locale) {
        Object[] params = new Object[]{Entity.VENDOR.getName(), Entity.VendorField.VENDOR_GUID.getFieldName(), vendorGuid};
        return vendorRepository.findById(vendorGuid)
            .orElseThrow(() ->
                new EntityNotFoundException(messageSource.getMessage(Error.ENTITY_NOT_FOUND.getKey(), params, locale)));
    }
}
