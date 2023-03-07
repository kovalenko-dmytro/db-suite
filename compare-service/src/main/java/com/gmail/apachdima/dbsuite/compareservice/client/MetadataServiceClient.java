package com.gmail.apachdima.dbsuite.compareservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "metadata-service", url = "${client.metadata-service.base-uri}")
public interface MetadataServiceClient {

}
