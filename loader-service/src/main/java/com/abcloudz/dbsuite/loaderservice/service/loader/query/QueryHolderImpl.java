package com.abcloudz.dbsuite.loaderservice.service.loader.query;

import com.abcloudz.dbsuite.loaderservice.common.message.Error;
import com.abcloudz.dbsuite.loaderservice.exception.LoaderServiceApplicationException;
import com.abcloudz.dbsuite.loaderservice.model.category.VendorType;
import com.abcloudz.dbsuite.loaderservice.model.version.Version;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class QueryHolderImpl implements QueryHolder {

    private static final String QUERY_RESOURCE_PATH = "queries/{0}/version_{1}.properties";

    private final MessageSource messageSource;
    private final Map<VendorType, List<Version>> resourceVersions = Map.of(
        VendorType.POSTGRESQL, List.of(new Version("9.2"), new Version("9.4"), new Version("10.0"),new Version("11.2"), new Version("12.0"))
    );

    @Override
    public String getQuery(VendorType vendorType, Version serverVersion, QueryKey queryKey, Locale locale) {
        List<Version> vendorResourceVersions = resourceVersions.get(vendorType);
        if (Objects.isNull(vendorResourceVersions)) {
            throw new LoaderServiceApplicationException(
                messageSource.getMessage(Error.VENDOR_UNSUPPORTED.getKey(), new Object[]{vendorType}, locale));
        }
        List<String> filteredResourceVersions = filterResourceVersions(vendorResourceVersions, serverVersion);
        return findQuery(vendorType, queryKey, filteredResourceVersions, locale);
    }

    private String findQuery(VendorType vendorType, QueryKey queryKey, List<String> filteredResourceVersions, Locale locale) {
        String query = null;
        try {
            Properties properties;
            String path;
            for (String resourceVersion : filteredResourceVersions) {
                path = MessageFormat.format(QUERY_RESOURCE_PATH, vendorType.getVendorType(), resourceVersion);
                properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource(path));
                query = properties.getProperty(queryKey.getKey());
                if (Objects.nonNull(query)) {
                    break;
                }
            }
            return Optional
                .ofNullable(query)
                .orElseThrow(() ->
                    new LoaderServiceApplicationException(messageSource.getMessage(
                        Error.LOADER_QUERY_NOT_DEFINED.getKey(), new Object[]{queryKey, vendorType}, locale)));
        } catch (IOException e) {
            throw new LoaderServiceApplicationException(e.getMessage());
        }
    }

    private List<String> filterResourceVersions(List<Version> vendorResourceVersions, Version serverVersion) {
        List<String> result = vendorResourceVersions.stream()
            .filter(version -> version.compareTo(serverVersion) <= 0)
            .sorted(Comparator.reverseOrder())
            .map(Version::toString)
            .collect(Collectors.toList());
        if (result.isEmpty()) {
            result.add(Collections.min(vendorResourceVersions).toString());
        }
        return result;
    }
}
