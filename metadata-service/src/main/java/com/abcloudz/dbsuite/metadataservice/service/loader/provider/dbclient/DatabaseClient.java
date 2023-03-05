package com.abcloudz.dbsuite.metadataservice.service.loader.provider.dbclient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DatabaseClient<T> {

    private T client;
}
