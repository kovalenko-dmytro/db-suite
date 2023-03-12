package com.gmail.apachdima.dbsuite.compareservice.dto.compare;

import com.gmail.apachdima.dbsuite.compareservice.dto.matcher.MatchNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CompareContext {

    private MatchNode matchNode;
    private String anotherProperty;
}
