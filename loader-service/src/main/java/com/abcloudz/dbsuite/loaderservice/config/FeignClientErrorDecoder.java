package com.abcloudz.dbsuite.loaderservice.config;

import com.abcloudz.dbsuite.loaderservice.exception.FeignClientException;
import com.abcloudz.dbsuite.loaderservice.exception.LoaderServiceApplicationException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        FeignClientException exception;
        try (InputStream bodyIs = response.body().asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            exception = mapper.readValue(bodyIs, FeignClientException.class);
            return new LoaderServiceApplicationException(exception.convert());
        } catch (IOException e) {
            return new Exception(e.getMessage());
        }
    }
}
