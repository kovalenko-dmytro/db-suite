package com.abcloudz.dbsuite.someservice.exception;

public class SomeServiceApplicationException extends RuntimeException {

    public SomeServiceApplicationException() {
        super();
    }

    public SomeServiceApplicationException(String message) {
        super(message);
    }
}
