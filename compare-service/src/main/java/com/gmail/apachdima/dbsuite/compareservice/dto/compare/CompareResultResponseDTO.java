package com.gmail.apachdima.dbsuite.compareservice.dto.compare;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CompareResultResponseDTO {

    private CompareResult result;
}
