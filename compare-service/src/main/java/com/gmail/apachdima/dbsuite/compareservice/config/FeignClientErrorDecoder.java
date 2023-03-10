package com.gmail.apachdima.dbsuite.compareservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.apachdima.dbsuite.compareservice.exception.CompareServiceApplicationException;
import com.gmail.apachdima.dbsuite.compareservice.exception.FeignClientExceptionWrapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class FeignClientErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        FeignClientExceptionWrapper exceptionWrapper;
        try (InputStream bodyIs = response.body().asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            exceptionWrapper = mapper.readValue(bodyIs, FeignClientExceptionWrapper.class);
            return new CompareServiceApplicationException(exceptionWrapper.convert());
        } catch (IOException e) {
            return new CompareServiceApplicationException(e.getMessage());
        }
    }
}
