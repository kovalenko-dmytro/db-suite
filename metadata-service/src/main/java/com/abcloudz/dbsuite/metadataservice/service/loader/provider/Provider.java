package com.abcloudz.dbsuite.metadataservice.service.loader.provider;

import java.util.Locale;

public interface Provider<R, C> {

    R provide(C context, Locale locale);
}
