package com.abcloudz.dbsuite.loaderservice.service.loader.provider.vendor;

import com.abcloudz.dbsuite.loaderservice.common.message.Error;
import com.abcloudz.dbsuite.loaderservice.model.category.MetadataCategoryType;
import com.abcloudz.dbsuite.loaderservice.model.category.VendorType;
import com.abcloudz.dbsuite.loaderservice.service.loader.metadataloader.MetadataLoader;
import com.abcloudz.dbsuite.loaderservice.service.loader.provider.Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VendorLoaderProvider implements Provider<Provider<MetadataLoader, MetadataCategoryType>, VendorType> {

    private final ApplicationContext context;
    private final MessageSource messageSource;

    private final Map<VendorType, Provider<MetadataLoader, MetadataCategoryType>> providers = new EnumMap<>(VendorType.class);

    @Override
    public Provider<MetadataLoader, MetadataCategoryType> provide(VendorType vendorType, Locale locale) {
        return Optional
            .ofNullable(providers.get(vendorType))
            .orElseThrow(() ->
                new UnsupportedOperationException(
                    messageSource.getMessage(Error.VENDOR_UNSUPPORTED.getKey(), new Object[]{vendorType}, locale)));
    }

    @PostConstruct
    private void init() {
        providers.put(VendorType.POSTGRESQL, getBeanByName("PostgreSqlMetadataLoaderProvider"));
        providers.put(VendorType.MYSQL, getBeanByName("MySqlMetadataLoaderProvider"));
        providers.put(VendorType.MONGO_DB, getBeanByName("MongoDbMetadataLoaderProvider"));
    }

    private Provider<MetadataLoader, MetadataCategoryType> getBeanByName(String beanName) {
        return context.getBean(beanName, Provider.class);
    }
}
