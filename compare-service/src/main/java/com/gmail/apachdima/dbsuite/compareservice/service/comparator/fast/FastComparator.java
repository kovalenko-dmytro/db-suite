package com.gmail.apachdima.dbsuite.compareservice.service.comparator.fast;

import com.gmail.apachdima.dbsuite.compareservice.dto.compare.CompareContext;
import com.gmail.apachdima.dbsuite.compareservice.dto.compare.CompareResult;
import com.gmail.apachdima.dbsuite.compareservice.service.comparator.Comparator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FastComparator implements Comparator<CompareContext, CompareResult> {

    @Override
    public CompareResult compare(CompareContext context) {
        return null;
    }
}
