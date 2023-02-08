package com.abcloudz.dbsuite.loaderservice.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Entity {

    CATEGORY("Metadata category");

    private final String name;

    @AllArgsConstructor
    @Getter
    public enum CategoryField {

        CATEGORY_GUID("guid");

        private final String fieldName;
    }
}
