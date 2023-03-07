package com.gmail.apachdima.dbsuite.compareservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "vendor-service", url = "${client.vendor-service.base-uri}")
public interface VendorServiceClient {

}
