package com.abcloudz.springmicroservicestemplate.userservice.exception;

import org.springframework.dao.DataAccessException;

public class EntityNotFoundException extends DataAccessException {

    public EntityNotFoundException(String message) {
        super(message);
    }
}
