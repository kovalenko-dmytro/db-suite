package com.gmail.apachdima.dbsuite.metadataservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.apachdima.dbsuite.metadataservice.exception.FeignClientExceptionWrapper;
import com.gmail.apachdima.dbsuite.metadataservice.exception.MetadataServiceApplicationException;
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
            return new MetadataServiceApplicationException(exceptionWrapper.convert());
        } catch (IOException e) {
            return new MetadataServiceApplicationException(e.getMessage());
        }
    }
}
