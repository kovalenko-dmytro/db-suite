package com.gmail.apachdima.dbsuite.compareservice.controller;

import com.gmail.apachdima.dbsuite.compareservice.dto.compare.CompareRequestDTO;
import com.gmail.apachdima.dbsuite.compareservice.dto.compare.CompareResultResponseDTO;
import com.gmail.apachdima.dbsuite.compareservice.service.CompareService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

@Tag(name = "Compare REST API")
@RestController
@RequestMapping(value = "/api/v1/compare")
@RequiredArgsConstructor
public class CompareController {

    private final CompareService compareService;

    @PostMapping
    public ResponseEntity<CompareResultResponseDTO> compare(@RequestBody @Valid CompareRequestDTO request,
                                                            @RequestParam(
                                                                value = "locale",
                                                                required = false,
                                                                defaultValue = "en") Locale locale) {
        return ResponseEntity.status(HttpStatus.CREATED).body(compareService.compare(request, locale));
    }
}
