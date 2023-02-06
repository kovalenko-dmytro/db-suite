package com.abcloudz.dbsuite.vendorservice.common.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Info {

    INFO_LOG_CONTROLLER_EXECUTE("info.log.controller.execute"),
    CONNECTION_VERIFIED("info.connection.verified");

    private final String key;
}
