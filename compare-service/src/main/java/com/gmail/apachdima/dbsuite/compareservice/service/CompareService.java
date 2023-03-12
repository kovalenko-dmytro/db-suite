package com.gmail.apachdima.dbsuite.compareservice.service;

import com.gmail.apachdima.dbsuite.compareservice.dto.compare.CompareRequestDTO;
import com.gmail.apachdima.dbsuite.compareservice.dto.compare.CompareResultResponseDTO;

import java.util.Locale;

public interface CompareService {

    CompareResultResponseDTO compare(CompareRequestDTO request, Locale locale);
}
