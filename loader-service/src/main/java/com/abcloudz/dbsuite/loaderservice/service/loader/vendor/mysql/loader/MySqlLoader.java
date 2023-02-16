package com.abcloudz.dbsuite.loaderservice.service.loader.vendor.mysql.loader;

import com.abcloudz.dbsuite.loaderservice.dto.connection.ConnectionResponseDTO;
import com.abcloudz.dbsuite.loaderservice.model.category.MetadataCategory;
import com.abcloudz.dbsuite.loaderservice.model.metadata.Metadata;
import com.abcloudz.dbsuite.loaderservice.service.loader.VendorLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component("MySqlLoader")
@RequiredArgsConstructor
public class MySqlLoader implements VendorLoader {

    @Override
    public List<Metadata> load(ConnectionResponseDTO connection, MetadataCategory category, Metadata parent, Locale locale) {
        throw new UnsupportedOperationException();
    }
}
