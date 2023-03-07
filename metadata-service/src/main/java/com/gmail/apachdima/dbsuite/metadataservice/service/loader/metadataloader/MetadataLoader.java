package com.gmail.apachdima.dbsuite.metadataservice.service.loader.metadataloader;

import com.gmail.apachdima.dbsuite.metadataservice.dto.loader.LoadContext;
import com.gmail.apachdima.dbsuite.metadataservice.model.metadata.Metadata;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public interface MetadataLoader {

    List<Metadata> loadMetadata(LoadContext loadContext);

    default List<Metadata> fillPropertiesByOwner(List<Metadata> source) {
        return source.stream()
            .peek(object -> object.getProperties().forEach(metadataProperty -> metadataProperty.setMetadata(object)))
            .collect(Collectors.toList());
    }

    default boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columns = rsmd.getColumnCount();
        for (int x = 1; x <= columns; x++) {
            if (columnName.equals(rsmd.getColumnName(x))) {
                return true;
            }
        }
        return false;
    }
}
