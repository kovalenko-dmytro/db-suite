package com.abcloudz.dbsuite.loaderservice.controller;

import com.abcloudz.dbsuite.loaderservice.dto.metadata.MetadataResponseDTO;
import com.abcloudz.dbsuite.loaderservice.service.MetadataService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Tag(name = "Metadata REST API")
@RestController
@RequestMapping(value = "/api/v1/load/metadata")
@RequiredArgsConstructor
public class MetadataController {

    private final MetadataService metadataService;

    @GetMapping(value = "/{metadataGuid}")
    public ResponseEntity<MetadataResponseDTO> findByGuid(@PathVariable("metadataGuid") String metadataGuid,
                                                          @RequestParam(value = "locale", required = false, defaultValue = "en") Locale locale) {
        return ResponseEntity.ok().body(metadataService.findByMetadataGuid(metadataGuid, locale));
    }

    @DeleteMapping(value = "/{metadataGuid}")
    public ResponseEntity<MetadataResponseDTO> delete(@PathVariable("metadataGuid") String metadataGuid,
                                                      @RequestParam(value = "locale", required = false, defaultValue = "en") Locale locale) {
        metadataService.delete(metadataGuid, locale);
        return ResponseEntity.ok().build();
    }
}
