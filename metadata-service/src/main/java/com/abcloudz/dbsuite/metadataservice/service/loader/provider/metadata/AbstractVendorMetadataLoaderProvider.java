package com.abcloudz.dbsuite.metadataservice.service.loader.provider.metadata;

import com.abcloudz.dbsuite.metadataservice.common.message.Error;
import com.abcloudz.dbsuite.metadataservice.model.category.MetadataCategoryType;
import com.abcloudz.dbsuite.metadataservice.service.loader.metadataloader.MetadataLoader;
import com.abcloudz.dbsuite.metadataservice.service.loader.provider.Provider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
public abstract class AbstractVendorMetadataLoaderProvider implements Provider<MetadataLoader, MetadataCategoryType> {

    private final ApplicationContext context;
    private final MessageSource messageSource;

    @Getter
    private final Map<MetadataCategoryType, MetadataLoader> metadataLoaders = new EnumMap<>(MetadataCategoryType.class);

    @Override
    public MetadataLoader provide(MetadataCategoryType type, Locale locale) {
        return Optional
            .ofNullable(metadataLoaders.get(type))
            .orElseThrow(() ->
                new UnsupportedOperationException(
                    messageSource.getMessage(Error.CATEGORY_UNSUPPORTED.getKey(), new Object[]{type.getType()}, locale)));
    }

    protected MetadataLoader getBeanByName(String beanName) {
        return context.getBean(beanName, MetadataLoader.class);
    }

    @PostConstruct
    protected abstract void init();
}
