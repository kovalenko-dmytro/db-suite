package com.abcloudz.dbsuite.vendorservice.model.connection;

import com.abcloudz.dbsuite.vendorservice.model.vendor.Vendor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "db_connection")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Connection {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "connection_", columnDefinition = "bpchar", unique = true, nullable = false)
    private String connectionGuid;

    @OneToOne
    @JoinColumn(name = "vendor_", nullable = false)
    private Vendor vendor;

    @Column(name = "connection_name", nullable = false)
    private String connectionName;

    @Column(name = "host", nullable = false)
    private String host;

    @Column(name = "port", nullable = false)
    private String port;

    @Column(name = "database", nullable = false)
    private String database;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "verify_server_certificate", nullable = false)
    private Boolean verifyServerCertificate;

    @Column(name = "use_ssl", nullable = false)
    private Boolean useSSL;

    @Column(name = "require_ssl", nullable = false)
    private Boolean requireSSL;

    @Column(name = "verified", nullable = false)
    private Boolean verified;

    @Column(name = "added_at", nullable = false)
    private LocalDateTime addedAt;
}
