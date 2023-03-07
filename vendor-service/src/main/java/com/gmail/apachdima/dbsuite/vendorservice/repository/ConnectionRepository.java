package com.gmail.apachdima.dbsuite.vendorservice.repository;

import com.gmail.apachdima.dbsuite.vendorservice.model.connection.Connection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, String> {

    Page<Connection> findByVendor_vendorGuid(String vendorGuid, Pageable pageable);
}
