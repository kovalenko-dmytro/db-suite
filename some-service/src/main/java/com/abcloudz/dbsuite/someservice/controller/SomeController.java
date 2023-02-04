package com.abcloudz.dbsuite.someservice.controller;

import com.abcloudz.dbsuite.someservice.service.SomeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Some Service REST API")
@RestController
@RequestMapping(value = "/api/v1/some")
@RequiredArgsConstructor
public class SomeController {

    private final SomeService someService;

    @GetMapping(value = "/some")
    public ResponseEntity<?> some() {
        return ResponseEntity.ok().build();
    }
}
