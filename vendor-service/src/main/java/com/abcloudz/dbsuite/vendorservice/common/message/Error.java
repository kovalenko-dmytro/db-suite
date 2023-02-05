package com.abcloudz.dbsuite.vendorservice.common.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Error {

    ENTITY_NOT_FOUND("error.entity.not.found"),
    VALIDATION_REQUEST("error.validation.request"),
    MISSING_REQUEST_PARAMETER("error.missing.request.parameter"),
    DATA_ACCESS("error.data.access"),
    NO_HANDLER_FOUND("error.no.handler.found"),
    HTTP_METHOD_NOT_ALLOWED("error.http.method.not.allowed"),
    MEDIA_TYPE_NOT_SUPPORTED("error.media.type.not.supported"),
    INTERNAL_SERVER_ERROR_OCCURRED("error.internal.server.error.occurred"),
    LOG_CONTROLLER_EXECUTE("error.log.controller.execute"),
    VENDOR_UNSUPPORTED("error.vendor.unsupported"),
    VENDOR_CONNECTION_UNVERIFIED("error.vendor.connection.unverified");

    private final String key;
}
