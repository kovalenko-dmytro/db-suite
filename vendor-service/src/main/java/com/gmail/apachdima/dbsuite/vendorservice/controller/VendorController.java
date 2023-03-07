package com.gmail.apachdima.dbsuite.vendorservice.controller;

import com.gmail.apachdima.dbsuite.vendorservice.dto.vendor.VendorResponseDTO;
import com.gmail.apachdima.dbsuite.vendorservice.service.general.VendorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Tag(name = "Vendor Service REST API")
@RestController
@RequestMapping(value = "/api/v1/vendors")
@RequiredArgsConstructor
public class VendorController {

    private final VendorService vendorService;

    @GetMapping
    public ResponseEntity<Page<VendorResponseDTO>> find(Pageable pageable) {
        return ResponseEntity.ok().body(vendorService.find(pageable));
    }

    @GetMapping(value = "/{vendorGuid}")
    public ResponseEntity<VendorResponseDTO> findByGuid(@PathVariable("vendorGuid") String vendorGuid,
                                                        @RequestParam(value = "locale", required = false, defaultValue = "en") Locale locale) {
        return ResponseEntity.ok().body(vendorService.findByGuid(vendorGuid, locale));
    }
}
