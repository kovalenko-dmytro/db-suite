package com.abcloudz.dbsuite.loaderservice.service.loader.provider.metadata;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component("MySqlMetadataLoaderProvider")
public class MySqlMetadataLoaderProvider extends AbstractVendorMetadataLoaderProvider {

    public MySqlMetadataLoaderProvider(ApplicationContext context, MessageSource messageSource) {
        super(context, messageSource);
    }

    @Override
    protected void init() {

    }
}
