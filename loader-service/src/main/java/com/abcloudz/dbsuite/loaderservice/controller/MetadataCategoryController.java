package com.abcloudz.dbsuite.loaderservice.controller;

import com.abcloudz.dbsuite.loaderservice.service.MetadataCategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Metadata Category REST API")
@RestController
@RequestMapping(value = "/api/v1/load/metadata-categories")
@RequiredArgsConstructor
public class MetadataCategoryController {

    private final MetadataCategoryService metadataCategoryService;


}
