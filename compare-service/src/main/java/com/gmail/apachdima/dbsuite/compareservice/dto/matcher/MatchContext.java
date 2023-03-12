package com.gmail.apachdima.dbsuite.compareservice.dto.matcher;

import com.gmail.apachdima.dbsuite.compareservice.common.CompareMode;
import com.gmail.apachdima.dbsuite.compareservice.dto.metadata.MetadataDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MatchContext {

    private MetadataDTO leftMetadata;
    private MetadataDTO rightMetadata;
    private CompareMode compareMode;
}
