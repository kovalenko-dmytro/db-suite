package com.abcloudz.dbsuite.loaderservice.service.loader.vendor.postgresql.provider;

import com.abcloudz.dbsuite.loaderservice.model.category.MetadataCategoryType;
import com.abcloudz.dbsuite.loaderservice.service.loader.provider.AbstractMetadataLoaderProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class PostgreSqlMetadataLoaderProvider extends AbstractMetadataLoaderProvider {

    public PostgreSqlMetadataLoaderProvider(ApplicationContext context, MessageSource messageSource) {
        super(context, messageSource);
    }

    @Override
    protected void init() {
        getMetadataLoaders().put(MetadataCategoryType.SERVERS, getBeanByName("ServerPostgreSqlLoader"));
        getMetadataLoaders().put(MetadataCategoryType.DATABASES, getBeanByName("DatabasePostgreSqlLoader"));
        getMetadataLoaders().put(MetadataCategoryType.SCHEMAS, getBeanByName("SchemaPostgreSqlLoader"));
    }
}
