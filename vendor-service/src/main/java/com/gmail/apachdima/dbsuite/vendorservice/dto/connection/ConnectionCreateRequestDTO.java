package com.gmail.apachdima.dbsuite.vendorservice.dto.connection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ConnectionCreateRequestDTO {

    @NotBlank
    private String vendorGuid;
    @NotBlank
    private String connectionName;
    @NotBlank
    private String host;
    @NotBlank
    private String port;
    @NotBlank
    private String database;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotNull
    private Boolean verifyServerCertificate;
    @NotNull
    private Boolean useSSL;
    @NotNull
    private Boolean requireSSL;
}
