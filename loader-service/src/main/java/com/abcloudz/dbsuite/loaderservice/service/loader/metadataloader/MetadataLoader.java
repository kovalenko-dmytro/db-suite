package com.abcloudz.dbsuite.loaderservice.service.loader.metadataloader;

import com.abcloudz.dbsuite.loaderservice.dto.loader.LoadContext;
import com.abcloudz.dbsuite.loaderservice.model.metadata.Metadata;

import java.util.List;
import java.util.stream.Collectors;

public interface MetadataLoader {

    List<Metadata> loadMetadata(LoadContext loadContext);

    default List<Metadata> fillPropertiesByOwner(List<Metadata> source) {
        return source.stream()
            .peek(object -> object.getProperties().forEach(metadataProperty -> metadataProperty.setMetadata(object)))
            .collect(Collectors.toList());
    }
}
