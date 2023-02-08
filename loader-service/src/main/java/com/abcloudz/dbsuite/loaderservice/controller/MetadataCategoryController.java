package com.abcloudz.dbsuite.loaderservice.controller;

import com.abcloudz.dbsuite.loaderservice.dto.category.MetadataCategoryResponseDTO;
import com.abcloudz.dbsuite.loaderservice.service.MetadataCategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Tag(name = "Metadata Category REST API")
@RestController
@RequestMapping(value = "/api/v1/load/metadata-categories")
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
