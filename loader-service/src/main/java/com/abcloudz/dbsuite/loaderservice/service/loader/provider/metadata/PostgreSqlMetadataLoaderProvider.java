package com.abcloudz.dbsuite.loaderservice.service.loader.provider.metadata;

import com.abcloudz.dbsuite.loaderservice.model.category.MetadataCategoryType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component("PostgreSqlMetadataLoaderProvider")
public class PostgreSqlMetadataLoaderProvider extends AbstractVendorMetadataLoaderProvider {

    public PostgreSqlMetadataLoaderProvider(ApplicationContext context, MessageSource messageSource) {
        super(context, messageSource);
    }

    @Override
    protected void init() {
        getMetadataLoaders().put(MetadataCategoryType.SERVERS, getBeanByName("ServerPostgreSqlLoader"));
        getMetadataLoaders().put(MetadataCategoryType.DATABASES, getBeanByName("DatabasePostgreSqlLoader"));
        getMetadataLoaders().put(MetadataCategoryType.SCHEMAS, getBeanByName("SchemaPostgreSqlLoader"));
        getMetadataLoaders().put(MetadataCategoryType.EXTENSIONS, getBeanByName("ExtensionPostgreSqlLoader"));
        getMetadataLoaders().put(MetadataCategoryType.TABLES, getBeanByName("TablePostgreSqlLoader"));
        getMetadataLoaders().put(MetadataCategoryType.VIEWS, getBeanByName("ViewPostgreSqlLoader"));
    }
}
