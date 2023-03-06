package com.abcloudz.dbsuite.metadataservice.repository;

import com.abcloudz.dbsuite.metadataservice.model.metadata.Metadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public interface MetadataRepository extends JpaRepository<Metadata, String> {

    void deleteByConnectionGuidAndCategory_metadataCategoryGuidAndParent_metadataGuid(String connectionGuid,
                                                                                      String metadataCategoryGuid,
                                                                                      @Nullable String metadataGuid);

    @Modifying
    @Query(value = "DELETE from metadata WHERE metadata_ = :metadataGuid", nativeQuery = true)
    void delete(String metadataGuid);
}
