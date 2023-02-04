package com.abcloudz.dbsuite.vendorservice.repository;

import com.abcloudz.dbsuite.vendorservice.model.connection.Connection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, String> {
}
