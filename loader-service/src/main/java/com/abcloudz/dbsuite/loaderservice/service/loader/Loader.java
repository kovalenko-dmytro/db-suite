package com.abcloudz.dbsuite.loaderservice.service.loader;

import com.abcloudz.dbsuite.loaderservice.dto.loader.LoadContext;
import com.abcloudz.dbsuite.loaderservice.model.category.MetadataCategoryType;
import com.abcloudz.dbsuite.loaderservice.model.category.VendorType;
import com.abcloudz.dbsuite.loaderservice.model.databaseclient.DatabaseClient;
import com.abcloudz.dbsuite.loaderservice.model.metadata.Metadata;
import com.abcloudz.dbsuite.loaderservice.service.loader.metadataloader.MetadataLoader;
import com.abcloudz.dbsuite.loaderservice.service.loader.provider.Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class Loader implements ILoader {

    private final Provider<Provider<MetadataLoader, MetadataCategoryType>, VendorType> vendorLoaderProvider;
    private final Provider<DatabaseClient<?>, LoadContext> databaseClientProvider;
    private final Provider<String, LoadContext> queryProvider;

    @Override
    public List<Metadata> load(LoadContext loadContext, Locale locale) {
        loadContext.setDatabaseClient(databaseClientProvider.provide(loadContext, locale));
        loadContext.setQuery(queryProvider.provide(loadContext, locale));

        VendorType vendorType = VendorType.getType(loadContext.getConnection().getVendor().getType());
        Provider<MetadataLoader, MetadataCategoryType> vendorProvider = vendorLoaderProvider.provide(vendorType, locale);
        MetadataLoader metadataLoader = vendorProvider.provide(loadContext.getCategory().getType(), locale);

        return metadataLoader.loadMetadata(loadContext);
    }
}
