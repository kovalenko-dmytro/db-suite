package com.gmail.apachdima.dbsuite.compareservice.client;

import com.gmail.apachdima.dbsuite.compareservice.dto.metadata.MetadataDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

@FeignClient(name = "metadata-service", url = "${client.metadata-service.base-uri}")
public interface MetadataServiceClient {

    @GetMapping(value = "/api/v1/metadata/{metadataGuid}")
    MetadataDTO findByGuid(@PathVariable("metadataGuid") String metadataGuid, @RequestParam("locale") Locale locale);
}
