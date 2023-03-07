package com.gmail.apachdima.dbsuite.compareservice.dto.connection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VendorResponseDTO {

    private String vendorGuid;
    private String type;
    private String displayName;
    private String driver;
    private boolean rdbms;
    private String jdbcName;
    private LocalDateTime addedAt;
}
