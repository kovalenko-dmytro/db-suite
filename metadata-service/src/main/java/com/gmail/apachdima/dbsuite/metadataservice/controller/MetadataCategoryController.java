package com.gmail.apachdima.dbsuite.metadataservice.controller;

import com.gmail.apachdima.dbsuite.metadataservice.dto.category.MetadataCategoryResponseDTO;
import com.gmail.apachdima.dbsuite.metadataservice.service.category.MetadataCategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Tag(name = "Metadata Category REST API")
@RestController
@RequestMapping(value = "/api/v1/metadata-categories")
@RequiredArgsConstructor
public class MetadataCategoryController {

    private final MetadataCategoryService metadataCategoryService;

    @GetMapping(value = "/root")
    public ResponseEntity<MetadataCategoryResponseDTO> findRoot(@RequestParam("vendorType") String vendorType,
                                                                @RequestParam(value = "locale", required = false, defaultValue = "en") Locale locale) {
        return ResponseEntity.ok().body(metadataCategoryService.findRootMetadataCategory(vendorType, locale));
    }

    @GetMapping(value = "/{metadataCategoryGuid}")
    public ResponseEntity<MetadataCategoryResponseDTO> findByGuid(@PathVariable("metadataCategoryGuid") String metadataCategoryGuid,
                                                                  @RequestParam(value = "locale", required = false, defaultValue = "en") Locale locale) {
        return ResponseEntity.ok().body(metadataCategoryService.findByMetadataCategoryGuid(metadataCategoryGuid, locale));
    }
}
