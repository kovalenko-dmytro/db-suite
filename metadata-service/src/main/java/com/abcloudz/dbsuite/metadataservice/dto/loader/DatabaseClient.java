package com.abcloudz.dbsuite.metadataservice.dto.loader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DatabaseClient<T> {

    private T client;
}
