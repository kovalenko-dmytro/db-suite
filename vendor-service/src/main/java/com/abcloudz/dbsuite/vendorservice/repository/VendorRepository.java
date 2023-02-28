package com.abcloudz.dbsuite.vendorservice.repository;

import com.abcloudz.dbsuite.vendorservice.model.vendor.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, String> {

}
