package com.abcloudz.dbsuite.loaderservice.service.loader.provider;

import com.abcloudz.dbsuite.loaderservice.common.message.Error;
import com.abcloudz.dbsuite.loaderservice.model.category.VendorType;
import com.abcloudz.dbsuite.loaderservice.service.loader.VendorLoader;
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
public class VendorLoaderProvider {

    private final ApplicationContext context;
    private final MessageSource messageSource;

    private final Map<VendorType, VendorLoader> vendorLoaders = new EnumMap<>(VendorType.class);

    public VendorLoader getVendorLoader(VendorType vendorType, Locale locale) {
        return Optional
            .ofNullable(vendorLoaders.get(vendorType))
            .orElseThrow(() -> new UnsupportedOperationException(getMessage(vendorType, locale)));
    }

    private String getMessage(VendorType vendorType, Locale locale) {
        return messageSource
            .getMessage(Error.VENDOR_UNSUPPORTED.getKey(), new Object[]{vendorType.getVendorType()}, locale);
    }

    @PostConstruct
    private void init() {
        vendorLoaders.put(VendorType.POSTGRESQL, getBeanByName("PostgreSqlLoader"));
        vendorLoaders.put(VendorType.MYSQL, getBeanByName("MySqlLoader"));
        vendorLoaders.put(VendorType.MONGO_DB, getBeanByName("MongoDbLoader"));
    }

    private VendorLoader getBeanByName(String beanName) {
        return context.getBean(beanName, VendorLoader.class);
    }
}
