package com.abcloudz.dbsuite.userservice.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Entity {

    USER("User");

    private final String name;

    @AllArgsConstructor
    @Getter
    public enum UserField {

        USER_ID("id"),
        USER_NAME("userName");

        private final String fieldName;
    }
}
