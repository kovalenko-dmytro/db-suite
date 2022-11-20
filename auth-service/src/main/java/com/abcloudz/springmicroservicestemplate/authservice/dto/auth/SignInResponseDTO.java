package com.abcloudz.springmicroservicestemplate.authservice.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SignInResponseDTO {

    private String accessToken;
    private String refreshToken;
}
