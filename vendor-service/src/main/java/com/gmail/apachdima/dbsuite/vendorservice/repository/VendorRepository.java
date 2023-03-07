package com.gmail.apachdima.dbsuite.vendorservice.repository;

import com.gmail.apachdima.dbsuite.vendorservice.model.vendor.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, String> {

}
