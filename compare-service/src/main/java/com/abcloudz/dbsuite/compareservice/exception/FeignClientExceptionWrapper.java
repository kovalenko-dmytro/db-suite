package com.abcloudz.dbsuite.compareservice.exception;

import com.abcloudz.dbsuite.compareservice.common.CommonConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FeignClientExceptionWrapper {

    private String timestamp;
    private String status;
    private String[] errors;
    private String message;
    private String path;

    public String convert() {
        return Arrays.stream(this.getErrors())
            .sequential()
            .collect(Collectors.joining(CommonConstant.COMMA.getValue()));
    }
}
