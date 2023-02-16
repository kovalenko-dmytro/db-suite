package com.abcloudz.dbsuite.loaderservice.service.loader;

import com.abcloudz.dbsuite.loaderservice.dto.connection.ConnectionResponseDTO;
import com.abcloudz.dbsuite.loaderservice.model.category.MetadataCategory;
import com.abcloudz.dbsuite.loaderservice.model.metadata.Metadata;

import java.util.List;
import java.util.Locale;

public interface VendorLoader {

    List<Metadata> load(ConnectionResponseDTO connection, MetadataCategory category, Metadata parent, Locale locale);
}
