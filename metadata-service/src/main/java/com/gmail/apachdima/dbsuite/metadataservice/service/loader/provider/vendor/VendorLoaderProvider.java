package com.gmail.apachdima.dbsuite.metadataservice.service.loader.provider.vendor;

import com.gmail.apachdima.dbsuite.metadataservice.common.message.Error;
import com.gmail.apachdima.dbsuite.metadataservice.model.category.MetadataCategoryType;
import com.gmail.apachdima.dbsuite.metadataservice.model.category.VendorType;
import com.gmail.apachdima.dbsuite.metadataservice.service.loader.metadataloader.MetadataLoader;
import com.gmail.apachdima.dbsuite.metadataservice.service.loader.provider.Provider;
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
