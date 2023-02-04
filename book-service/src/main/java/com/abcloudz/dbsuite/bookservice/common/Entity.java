package com.abcloudz.dbsuite.bookservice.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Entity {

    BOOK("Book"),
    USER("User");

    private final String name;

    @AllArgsConstructor
    @Getter
    public enum BookField {

        BOOK_ID("id");

        private final String fieldName;
    }

    @AllArgsConstructor
    @Getter
    public enum UserField {

        USER_ID("id"),
        USER_NAME("userName");

        private final String fieldName;
    }
}
