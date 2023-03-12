package com.gmail.apachdima.dbsuite.compareservice.common;

import java.util.Arrays;

public enum CompareMode {

    SCHEMA, FAST, DETAIL;

    public static CompareMode toCompareMode(String value) {
        return Arrays.stream(CompareMode.values())
            .filter(compareMode -> compareMode.name().contentEquals(value))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Compare mode <" + value + "> not found"));
    }
}
