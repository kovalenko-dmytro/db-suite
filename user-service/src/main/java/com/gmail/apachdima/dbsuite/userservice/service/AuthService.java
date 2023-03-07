package com.gmail.apachdima.dbsuite.userservice.service;

import com.gmail.apachdima.dbsuite.userservice.dto.auth.SignInRequestDTO;
import com.gmail.apachdima.dbsuite.userservice.dto.auth.SignUpRequestDTO;
import com.gmail.apachdima.dbsuite.userservice.dto.user.UserResponseDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public interface AuthService {

    void signIn(SignInRequestDTO signInRequestDTO);
    void signUp(SignUpRequestDTO signUpRequestDTO);
    UserResponseDTO getCurrentUser(Locale locale);
    void logout(HttpServletRequest request);
}
