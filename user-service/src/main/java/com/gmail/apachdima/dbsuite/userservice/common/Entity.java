package com.gmail.apachdima.dbsuite.userservice.common;

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

        USER_GUID("guid"),
        USER_NAME("userName");

        private final String fieldName;
    }
}
