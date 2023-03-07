package com.abcloudz.dbsuite.compareservice.common.message;

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
    SEARCH_CRITERIA_OPERATION_NOT_SUPPORTED("error.search.criteria.operation.not.supported"),
    CONVERSION_FAILED("error.conversion.failed"),
    LOG_CONTROLLER_EXECUTE("error.log.controller.execute"),
    SIGNUP_FAILED("error.signup.failed"),
    VENDOR_UNSUPPORTED("error.vendor.unsupported"),
    CATEGORY_UNSUPPORTED("error.category.unsupported"),
    CATEGORY_VERSION_UNSUPPORTED("error.category.version.unsupported"),
    CATEGORY_ROOT_NOT_FOUND("error.category.root.not.found"),
    LOADER_METADATA_PARENT_NOT_LOADED("loader.metadata.parent.not.loaded"),
    LOADER_QUERY_NOT_DEFINED("loader.metadata.query.not.defined"),
    DATABASE_CLIENT_UNSUPPORTED("database.client.unsupported");

    private final String key;
}
