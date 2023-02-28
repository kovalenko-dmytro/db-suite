package com.abcloudz.dbsuite.loaderservice.client;

import com.abcloudz.dbsuite.loaderservice.dto.connection.ConnectionResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;


@FeignClient(name = "vendor-service", url = "${client.vendor-service.base-uri}")
public interface VendorServiceClient {

    @GetMapping(value = "/api/v1/vendors/{vendorGuid}/connections/{connectionGuid}")
    ConnectionResponseDTO findByGuid(@PathVariable("vendorGuid") String vendorGuid,
                                     @PathVariable("connectionGuid") String connectionGuid,
                                     @RequestParam("locale") Locale locale);
}
