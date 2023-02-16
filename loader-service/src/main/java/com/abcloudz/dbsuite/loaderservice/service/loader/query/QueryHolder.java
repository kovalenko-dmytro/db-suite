package com.abcloudz.dbsuite.loaderservice.service.loader.query;

import com.abcloudz.dbsuite.loaderservice.model.category.VendorType;
import com.abcloudz.dbsuite.loaderservice.model.version.Version;

import java.util.Locale;

public interface QueryHolder {

    String getQuery(VendorType vendorType, Version serverVersion, QueryKey queryKey, Locale locale);
}
