package com.gmail.apachdima.dbsuite.compareservice.service.provider;

import com.gmail.apachdima.dbsuite.compareservice.dto.compare.CompareContext;
import com.gmail.apachdima.dbsuite.compareservice.dto.compare.CompareResult;
import com.gmail.apachdima.dbsuite.compareservice.service.CompareMode;
import com.gmail.apachdima.dbsuite.compareservice.service.comparator.Comparator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ComparatorProvider implements Provider<CompareMode, Comparator<CompareContext, CompareResult>> {

    private final Comparator<CompareContext, CompareResult> schemaComparator;
    private final Comparator<CompareContext, CompareResult> fastComparator;
    private final Comparator<CompareContext, CompareResult> detailComparator;

    @Override
    public Comparator<CompareContext, CompareResult> provide(CompareMode type) {
        switch (type) {
            case SCHEMA:
                return schemaComparator;
            case FAST:
                return fastComparator;
            case DETAIL:
                return detailComparator;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
