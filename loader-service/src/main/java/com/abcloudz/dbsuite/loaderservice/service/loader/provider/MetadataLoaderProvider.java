package com.abcloudz.dbsuite.loaderservice.service.loader.provider;

import com.abcloudz.dbsuite.loaderservice.model.category.MetadataCategoryType;
import com.abcloudz.dbsuite.loaderservice.service.loader.MetadataLoader;

import java.util.Locale;

public interface MetadataLoaderProvider<DC> {

    MetadataLoader<DC> getMetadataLoader(MetadataCategoryType categoryType, Locale locale);
}
