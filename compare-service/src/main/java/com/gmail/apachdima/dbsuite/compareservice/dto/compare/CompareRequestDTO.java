package com.gmail.apachdima.dbsuite.compareservice.dto.compare;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompareRequestDTO {

    @NotBlank
    private String leftMetadataGuid;
    @NotBlank
    private String rightMetadataGuid;
    @NotBlank
    private String compareMode;
}
