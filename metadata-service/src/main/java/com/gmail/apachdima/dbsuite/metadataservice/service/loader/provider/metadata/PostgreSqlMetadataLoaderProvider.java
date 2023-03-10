package com.gmail.apachdima.dbsuite.metadataservice.service.loader.provider.metadata;

import com.gmail.apachdima.dbsuite.metadataservice.model.category.MetadataCategoryType;
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
        getMetadataLoaders().put(MetadataCategoryType.MATERIALIZED_VIEWS, getBeanByName("MaterializedViewPostgreSqlLoader"));
        getMetadataLoaders().put(MetadataCategoryType.PROCEDURES, getBeanByName("ProcedurePostgreSqlLoader"));
        getMetadataLoaders().put(MetadataCategoryType.FUNCTIONS, getBeanByName("FunctionPostgreSqlLoader"));
        getMetadataLoaders().put(MetadataCategoryType.AGGREGATE_FUNCTIONS, getBeanByName("AggregateFunctionPostgreSqlLoader"));
        getMetadataLoaders().put(MetadataCategoryType.SEQUENCES, getBeanByName("SequencePostgreSqlLoader"));
        getMetadataLoaders().put(MetadataCategoryType.USER_DEFINED_TYPES, getBeanByName("UserDefinedTypePostgreSqlLoader"));
        getMetadataLoaders().put(MetadataCategoryType.PARTITIONS, getBeanByName("PartitionPostgreSqlLoader"));
        getMetadataLoaders().put(MetadataCategoryType.COLUMNS, getBeanByName("ColumnPostgreSqlLoader"));
        getMetadataLoaders().put(MetadataCategoryType.INDEXES, getBeanByName("IndexPostgreSqlLoader"));
        getMetadataLoaders().put(MetadataCategoryType.INDEX_COLUMNS, getBeanByName("IndexColumnPostgreSqlLoader"));
        getMetadataLoaders().put(MetadataCategoryType.TRIGGERS, getBeanByName("TriggerPostgreSqlLoader"));
        getMetadataLoaders().put(MetadataCategoryType.PRIMARY_KEYS, getBeanByName("ConstraintPostgreSqlLoader"));
        getMetadataLoaders().put(MetadataCategoryType.FOREIGN_KEYS, getBeanByName("ConstraintPostgreSqlLoader"));
        getMetadataLoaders().put(MetadataCategoryType.UNIQUE_KEYS, getBeanByName("ConstraintPostgreSqlLoader"));
        getMetadataLoaders().put(MetadataCategoryType.CONSTRAINT_COLUMNS, getBeanByName("ConstraintColumnPostgreSqlLoader"));
        getMetadataLoaders().put(MetadataCategoryType.CHECK_CONSTRAINTS, getBeanByName("CheckConstraintPostgreSqlLoader"));
        getMetadataLoaders().put(MetadataCategoryType.PARAMETERS, getBeanByName("ParameterPostgreSqlLoader"));
    }
}
