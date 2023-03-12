package com.gmail.apachdima.dbsuite.compareservice.service.provider;

public interface Provider<T, R> {

    R provide(T type);
}
