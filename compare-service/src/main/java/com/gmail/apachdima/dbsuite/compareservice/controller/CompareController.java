package com.gmail.apachdima.dbsuite.compareservice.controller;

import com.gmail.apachdima.dbsuite.compareservice.common.CompareMode;
import com.gmail.apachdima.dbsuite.compareservice.dto.compare.CompareRequestDTO;
import com.gmail.apachdima.dbsuite.compareservice.dto.compare.CompareResultResponseDTO;
import com.gmail.apachdima.dbsuite.compareservice.service.CompareService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Tag(name = "Compare REST API")
@RestController
@RequestMapping(value = "/api/v1/compare")
@RequiredArgsConstructor
public class CompareController {

    private final CompareService compareService;

    @GetMapping(value = "/modes")
    public ResponseEntity<List<String>> findCompareModes() {
        List<String> modes = Arrays.stream(CompareMode.values()).map(CompareMode::name).collect(Collectors.toList());
        return ResponseEntity.ok(modes);
    }

    @PostMapping
    public ResponseEntity<CompareResultResponseDTO> compare(@RequestBody @Valid CompareRequestDTO request,
                                                            @RequestParam(
                                                                value = "locale",
                                                                required = false,
                                                                defaultValue = "en") Locale locale) {
        return ResponseEntity.status(HttpStatus.CREATED).body(compareService.compare(request, locale));
    }
}
