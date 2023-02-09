package com.abcloudz.dbsuite.loaderservice.repository;

import com.abcloudz.dbsuite.loaderservice.model.metadata.Metadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetadataRepository extends JpaRepository<Metadata, String> {
}
