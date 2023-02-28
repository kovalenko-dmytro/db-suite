package com.abcloudz.dbsuite.loaderservice.service.loader.provider;

import java.util.Locale;

public interface Provider<R, C> {

    R provide(C context, Locale locale);
}
