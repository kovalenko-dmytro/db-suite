package com.gmail.apachdima.dbsuite.compareservice.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Entity {

    CATEGORY("Metadata category"),
    METADATA("Metadata"),
    CONNECTION("Connection");

    private final String name;

    @AllArgsConstructor
    @Getter
    public enum Field {

        GUID("guid");

        private final String fieldName;
    }
}
