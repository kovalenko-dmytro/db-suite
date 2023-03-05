package com.abcloudz.dbsuite.metadataservice.service.loader;

import com.abcloudz.dbsuite.metadataservice.dto.loader.LoadContext;
import com.abcloudz.dbsuite.metadataservice.model.metadata.Metadata;

import java.util.List;
import java.util.Locale;

public interface ILoader {

    List<Metadata> load(LoadContext loadContext, Locale locale);
}