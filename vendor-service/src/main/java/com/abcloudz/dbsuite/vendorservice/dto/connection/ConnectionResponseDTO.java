package com.abcloudz.dbsuite.vendorservice.dto.connection;

import com.abcloudz.dbsuite.vendorservice.dto.vendor.VendorResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ConnectionResponseDTO {

    private String connectionGuid;
    private VendorResponseDTO vendor;
    private String connectionName;
    private String host;
    private String port;
    private String database;
    private String username;
    private String password;
    private boolean verified;
}
