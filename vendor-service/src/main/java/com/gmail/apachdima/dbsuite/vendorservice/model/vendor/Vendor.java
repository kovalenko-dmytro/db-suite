package com.gmail.apachdima.dbsuite.vendorservice.model.vendor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vendor")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Vendor {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "vendor_", columnDefinition = "bpchar", unique = true, nullable = false)
    private String vendorGuid;

    @Column(name = "type", unique = true, nullable = false)
    private VendorType type;

    @Column(name = "display_name", nullable = false)
    private String displayName;

    @Column(name = "driver", nullable = false)
    private String driver;

    @Column(name = "is_rdbms", nullable = false)
    private Boolean rdbms;

    @Column(name = "jdbc_name")
    private String jdbcName;

    @Column(name = "added_at", nullable = false)
    private LocalDateTime addedAt;
}
