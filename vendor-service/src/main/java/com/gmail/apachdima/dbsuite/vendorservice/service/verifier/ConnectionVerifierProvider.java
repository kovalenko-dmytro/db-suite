package com.gmail.apachdima.dbsuite.vendorservice.service.verifier;

import com.gmail.apachdima.dbsuite.vendorservice.common.message.Error;
import com.gmail.apachdima.dbsuite.vendorservice.model.vendor.VendorType;
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
public class ConnectionVerifierProvider {

    private final ApplicationContext context;
    private final MessageSource messageSource;

    private final Map<VendorType, ConnectionVerifier> connectionVerifiers = new EnumMap<>(VendorType.class);

    public ConnectionVerifier getConnectionVerifier(VendorType vendorType, Locale locale) {
        return Optional
            .ofNullable(connectionVerifiers.get(vendorType))
            .orElseThrow(() -> new UnsupportedOperationException(getMessage(vendorType, locale)));
    }

    private String getMessage(VendorType vendorType, Locale locale) {
        return messageSource
            .getMessage(Error.VENDOR_UNSUPPORTED.getKey(), new Object[]{vendorType.getVendorType()}, locale);
    }

    @PostConstruct
    private void init() {
        connectionVerifiers.put(VendorType.POSTGRESQL, getBeanByName("RDBMSConnectionVerifier"));
        connectionVerifiers.put(VendorType.MYSQL, getBeanByName("RDBMSConnectionVerifier"));
        connectionVerifiers.put(VendorType.MONGO_DB, getBeanByName("MongoDBConnectionVerifier"));
    }

    private ConnectionVerifier getBeanByName(String beanName) {
        return context.getBean(beanName, ConnectionVerifier.class);
    }
}
