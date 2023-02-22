package com.abcloudz.dbsuite.loaderservice.service.loader.provider;

import com.abcloudz.dbsuite.loaderservice.common.message.Error;
import com.abcloudz.dbsuite.loaderservice.model.category.MetadataCategoryType;
import com.abcloudz.dbsuite.loaderservice.service.loader.MetadataLoader;
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
public abstract class AbstractMetadataLoaderProvider implements MetadataLoaderProvider {

    private final ApplicationContext context;
    private final MessageSource messageSource;

    @Getter
    private final Map<MetadataCategoryType, MetadataLoader> metadataLoaders = new EnumMap<>(MetadataCategoryType.class);

    @Override
    public MetadataLoader getMetadataLoader(MetadataCategoryType categoryType, Locale locale) {
        return Optional
            .ofNullable(metadataLoaders.get(categoryType))
            .orElseThrow(() -> new UnsupportedOperationException(getMessage(categoryType, locale)));
    }

    protected MetadataLoader getBeanByName(String beanName) {
        return context.getBean(beanName, MetadataLoader.class);
    }

    @PostConstruct
    protected abstract void init();

    private String getMessage(MetadataCategoryType categoryType, Locale locale) {
        return messageSource
            .getMessage(Error.CATEGORY_UNSUPPORTED.getKey(), new Object[]{categoryType.getType()}, locale);
    }
}
