package com.abcloudz.dbsuite.metadataservice.controller;

import com.abcloudz.dbsuite.metadataservice.dto.metadata.LoadMetadataRequestDTO;
import com.abcloudz.dbsuite.metadataservice.dto.metadata.MetadataResponseDTO;
import com.abcloudz.dbsuite.metadataservice.service.metadata.MetadataService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Tag(name = "Metadata REST API")
@RestController
@RequestMapping(value = "/api/v1/metadata")
@RequiredArgsConstructor
public class MetadataController {

    private final MetadataService metadataService;

    @GetMapping(value = "/{metadataGuid}")
    public ResponseEntity<MetadataResponseDTO> findByGuid(@PathVariable("metadataGuid") String metadataGuid,
                                                          @RequestParam(value = "locale", required = false, defaultValue = "en") Locale locale) {
        return ResponseEntity.ok().body(metadataService.findByMetadataGuid(metadataGuid, locale));
    }

    @PostMapping
    public ResponseEntity<List<MetadataResponseDTO>> load(@RequestBody @Valid LoadMetadataRequestDTO request,
                                                          @RequestParam(value = "full", defaultValue = "false") boolean full,
                                                          @RequestParam(value = "locale", required = false, defaultValue = "en") Locale locale) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(metadataService.load(request, full, locale));
    }

    @DeleteMapping(value = "/{metadataGuid}")
    public ResponseEntity<MetadataResponseDTO> delete(@PathVariable("metadataGuid") String metadataGuid,
                                                      @RequestParam(value = "locale", required = false, defaultValue = "en") Locale locale) {
        metadataService.delete(metadataGuid, locale);
        return ResponseEntity.ok().build();
    }
}
