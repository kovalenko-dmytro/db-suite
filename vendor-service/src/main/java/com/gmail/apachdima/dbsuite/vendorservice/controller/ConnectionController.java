package com.gmail.apachdima.dbsuite.vendorservice.controller;

import com.gmail.apachdima.dbsuite.vendorservice.dto.connection.ConnectionCreateRequestDTO;
import com.gmail.apachdima.dbsuite.vendorservice.dto.connection.ConnectionResponseDTO;
import com.gmail.apachdima.dbsuite.vendorservice.dto.connection.ConnectionUpdateRequestDTO;
import com.gmail.apachdima.dbsuite.vendorservice.dto.connection.ConnectionVerifyResponseDTO;
import com.gmail.apachdima.dbsuite.vendorservice.service.general.ConnectionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

@Tag(name = "DB Connection Service REST API")
@RestController
@RequestMapping(value = "/api/v1/connections")
@RequiredArgsConstructor
public class ConnectionController {

    private final ConnectionService connectionService;

    @GetMapping
    public ResponseEntity<Page<ConnectionResponseDTO>> find(@RequestParam(value = "vendorGuid") String vendorGuid,
                                                            Pageable pageable) {
        return ResponseEntity.ok().body(connectionService.find(vendorGuid, pageable));
    }

    @GetMapping(value = "/{connectionGuid}")
    public ResponseEntity<ConnectionResponseDTO> findByGuid(@PathVariable("connectionGuid") String connectionGuid,
                                                            @RequestParam(value = "locale", required = false, defaultValue = "en") Locale locale) {
        return ResponseEntity.ok().body(connectionService.findByGuid(connectionGuid, locale));
    }

    @PostMapping
    public ResponseEntity<ConnectionResponseDTO> create(@RequestBody @Valid ConnectionCreateRequestDTO request,
                                                        @RequestParam(value = "locale", required = false, defaultValue = "en") Locale locale) {
        return ResponseEntity.status(HttpStatus.CREATED).body(connectionService.create(request, locale));
    }

    @PutMapping(value = "/{connectionGuid}")
    public ResponseEntity<ConnectionResponseDTO> update(@PathVariable("connectionGuid") String connectionGuid,
                                                        @RequestBody @Valid ConnectionUpdateRequestDTO request,
                                                        @RequestParam(value = "locale", required = false, defaultValue = "en") Locale locale) {
        return ResponseEntity.ok().body(connectionService.update(connectionGuid, request, locale));
    }

    @PostMapping(value = "/{connectionGuid}/validate")
    public ResponseEntity<ConnectionVerifyResponseDTO> validate(@PathVariable("connectionGuid") String connectionGuid,
                                                                @RequestParam(value = "locale", required = false, defaultValue = "en") Locale locale) {
        return ResponseEntity.ok().body(connectionService.validate(connectionGuid, locale));
    }

    @DeleteMapping(value = "/{connectionGuid}")
    public ResponseEntity<?> delete(@PathVariable("connectionGuid") String connectionGuid,
                                    @RequestParam(value = "locale", required = false, defaultValue = "en") Locale locale) {
        connectionService.delete(connectionGuid, locale);
        return ResponseEntity.ok().build();
    }
}
