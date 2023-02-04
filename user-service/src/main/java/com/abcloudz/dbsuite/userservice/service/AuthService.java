package com.abcloudz.dbsuite.userservice.service;

import com.abcloudz.dbsuite.userservice.dto.auth.SignInRequestDTO;
import com.abcloudz.dbsuite.userservice.dto.auth.SignUpRequestDTO;
import com.abcloudz.dbsuite.userservice.dto.user.UserResponseDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public interface AuthService {

    void signIn(SignInRequestDTO signInRequestDTO);
    void signUp(SignUpRequestDTO signUpRequestDTO);
    UserResponseDTO getCurrentUser(Locale locale);
    void logout(HttpServletRequest request);
}
