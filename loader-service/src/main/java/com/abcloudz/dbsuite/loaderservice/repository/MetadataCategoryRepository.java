package com.abcloudz.dbsuite.loaderservice.repository;

import com.abcloudz.dbsuite.loaderservice.model.category.MetadataCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MetadataCategoryRepository extends JpaRepository<MetadataCategory, String> {

    Optional<MetadataCategory> findByVendorGuidAndRoot(String vendorGuid, boolean root);
}
