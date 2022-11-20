package com.abcloudz.springmicroservicestemplate.authservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${keycloak-server.server-url}")
    private String authServerBaseUrl;

    @Bean
    public WebClient authServerWebClient() {
        return WebClient.builder()
            .baseUrl(authServerBaseUrl)
            .build();
    }
}
