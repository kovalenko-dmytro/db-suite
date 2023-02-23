package com.abcloudz.dbsuite.loaderservice.service.loader.metadataloader;

import com.abcloudz.dbsuite.loaderservice.dto.loader.LoadContext;
import com.abcloudz.dbsuite.loaderservice.model.metadata.Metadata;

import java.util.List;

public interface MetadataLoader {

    List<Metadata> loadMetadata(LoadContext loadContext);
}
