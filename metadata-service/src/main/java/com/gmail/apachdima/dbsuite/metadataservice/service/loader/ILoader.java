package com.gmail.apachdima.dbsuite.metadataservice.service.loader;

import com.gmail.apachdima.dbsuite.metadataservice.dto.loader.LoadContext;
import com.gmail.apachdima.dbsuite.metadataservice.model.metadata.Metadata;

import java.util.List;
import java.util.Locale;

public interface ILoader {

    List<Metadata> load(LoadContext loadContext, Locale locale);
}
