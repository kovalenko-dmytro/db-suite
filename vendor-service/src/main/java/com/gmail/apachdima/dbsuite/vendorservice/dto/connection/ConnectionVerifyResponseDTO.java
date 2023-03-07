package com.gmail.apachdima.dbsuite.vendorservice.dto.connection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ConnectionVerifyResponseDTO {

    private boolean verified;
    private boolean isError;
    private String message;
}
