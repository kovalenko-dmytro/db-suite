package com.gmail.apachdima.dbsuite.metadataservice.repository;

import com.gmail.apachdima.dbsuite.metadataservice.model.category.MetadataCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetadataCategoryRepository extends JpaRepository<MetadataCategory, String> {

    List<MetadataCategory> findByRoot(boolean root);
}
