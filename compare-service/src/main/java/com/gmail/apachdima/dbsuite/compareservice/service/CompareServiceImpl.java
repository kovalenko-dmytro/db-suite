package com.gmail.apachdima.dbsuite.compareservice.service;

import com.gmail.apachdima.dbsuite.compareservice.client.MetadataServiceClient;
import com.gmail.apachdima.dbsuite.compareservice.common.CompareMode;
import com.gmail.apachdima.dbsuite.compareservice.dto.compare.CompareContext;
import com.gmail.apachdima.dbsuite.compareservice.dto.compare.CompareRequestDTO;
import com.gmail.apachdima.dbsuite.compareservice.dto.compare.CompareResult;
import com.gmail.apachdima.dbsuite.compareservice.dto.compare.CompareResultResponseDTO;
import com.gmail.apachdima.dbsuite.compareservice.dto.matcher.MatchContext;
import com.gmail.apachdima.dbsuite.compareservice.dto.matcher.MatchNode;
import com.gmail.apachdima.dbsuite.compareservice.dto.metadata.MetadataDTO;
import com.gmail.apachdima.dbsuite.compareservice.service.comparator.Comparator;
import com.gmail.apachdima.dbsuite.compareservice.service.matcher.IMatcher;
import com.gmail.apachdima.dbsuite.compareservice.service.provider.Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class CompareServiceImpl implements CompareService {

    private final MetadataServiceClient metadataServiceClient;
    private final IMatcher matcher;
    private final Provider<CompareMode, Comparator<CompareContext, CompareResult>> comparatorProvider;
    private final MessageSource messageSource;

    @Override
    public CompareResultResponseDTO compare(CompareRequestDTO request, Locale locale) {
        CompareMode compareMode = CompareMode.toCompareMode(request.getCompareMode());
        MetadataDTO leftMetadata = metadataServiceClient.findByGuid(request.getLeftMetadataGuid(), locale);
        MetadataDTO rightMetadata = metadataServiceClient.findByGuid(request.getLeftMetadataGuid(), locale);

        MatchContext matchContext = MatchContext.builder()
            .leftMetadata(leftMetadata).rightMetadata(rightMetadata).compareMode(compareMode)
            .build();
        MatchNode matchNode = matcher.match(matchContext);

        Comparator<CompareContext, CompareResult> comparator = comparatorProvider.provide(compareMode);
        CompareContext compareContext = CompareContext.builder().matchNode(matchNode).build();
        CompareResult compareResult = comparator.compare(compareContext);

        return CompareResultResponseDTO.builder().result(compareResult).build();
    }
}
