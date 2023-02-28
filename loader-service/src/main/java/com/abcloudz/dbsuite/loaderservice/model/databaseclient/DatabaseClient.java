package com.abcloudz.dbsuite.loaderservice.model.databaseclient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DatabaseClient<T> {

    private T client;
}
