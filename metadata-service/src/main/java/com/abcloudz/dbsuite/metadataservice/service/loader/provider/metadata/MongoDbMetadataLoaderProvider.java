package com.abcloudz.dbsuite.metadataservice.service.loader.provider.metadata;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component("MongoDbMetadataLoaderProvider")
public class MongoDbMetadataLoaderProvider extends AbstractVendorMetadataLoaderProvider {

    public MongoDbMetadataLoaderProvider(ApplicationContext context, MessageSource messageSource) {
        super(context, messageSource);
    }

    @Override
    protected void init() {

    }
}
