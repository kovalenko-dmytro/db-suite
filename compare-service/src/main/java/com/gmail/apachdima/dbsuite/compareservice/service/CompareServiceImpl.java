package com.gmail.apachdima.dbsuite.compareservice.service;

import com.gmail.apachdima.dbsuite.compareservice.dto.compare.CompareContext;
import com.gmail.apachdima.dbsuite.compareservice.dto.compare.CompareRequestDTO;
import com.gmail.apachdima.dbsuite.compareservice.dto.compare.CompareResult;
import com.gmail.apachdima.dbsuite.compareservice.dto.compare.CompareResultResponseDTO;
import com.gmail.apachdima.dbsuite.compareservice.service.comparator.Comparator;
import com.gmail.apachdima.dbsuite.compareservice.service.provider.Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class CompareServiceImpl implements CompareService {

    private final Provider<CompareMode, Comparator<CompareContext, CompareResult>> comparatorProvider;
    private final MessageSource messageSource;

    @Override
    public CompareResultResponseDTO compare(CompareRequestDTO request, Locale locale) {
        return null;
    }
}
