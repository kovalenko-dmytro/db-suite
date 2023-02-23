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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Loader implements ILoader {

    private final Provider<Provider<MetadataLoader, MetadataCategoryType>, VendorType> vendorLoaderProvider;
    private final Provider<DatabaseClient<?>, LoadContext> databaseClientProvider;
    private final Provider<String, LoadContext> queryProvider;

    @Override
    public List<Metadata> load(LoadContext loadContext, Locale locale) {
        DatabaseClient<?> databaseClient = databaseClientProvider.provide(loadContext, locale);
        loadContext.setDatabaseClient(databaseClient);

        String query = queryProvider.provide(loadContext, locale);
        loadContext.setQuery(query);

        VendorType vendorType = VendorType.getType(loadContext.getConnection().getVendor().getType());
        Provider<MetadataLoader, MetadataCategoryType> vendorProvider = vendorLoaderProvider.provide(vendorType, locale);
        MetadataLoader metadataLoader = vendorProvider.provide(loadContext.getCategory().getType(), locale);

        return metadataLoader.loadMetadata(loadContext).stream()
            .peek(object -> {
                object.setConnectionGuid(loadContext.getConnection().getConnectionGuid());
                object.setCategory(loadContext.getCategory());
                object.setParent(loadContext.getParent());
                object.getProperties().forEach(metadataProperty -> metadataProperty.setMetadata(object));
            })
            .collect(Collectors.toList());
    }
}
