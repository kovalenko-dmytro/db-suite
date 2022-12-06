package com.abcloudz.springmicroservicestemplate.userservice.service.impl;

import com.abcloudz.springmicroservicestemplate.userservice.dto.auth.SignInRequestDTO;
import com.abcloudz.springmicroservicestemplate.userservice.dto.auth.SignUpRequestDTO;
import com.abcloudz.springmicroservicestemplate.userservice.dto.user.UserResponseDTO;
import com.abcloudz.springmicroservicestemplate.userservice.model.User;
import com.abcloudz.springmicroservicestemplate.userservice.service.AuthService;
import com.abcloudz.springmicroservicestemplate.userservice.service.UserService;
import com.abcloudz.springmicroservicestemplate.userservice.util.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final SessionRepository<? extends Session> sessionRepository;
    private final UserMapper userMapper;

    @Override
    public void signIn(SignInRequestDTO request) {
        Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public void signUp(SignUpRequestDTO signUpRequestDTO) {
        userService.create(userMapper.toUserRequestDTO(signUpRequestDTO));
    }

    @Override
    public UserResponseDTO getCurrentUser(Locale locale) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.getUserByUserName(authentication.getName(), locale);
        return userMapper.toUserResponseDTO(currentUser);
    }

    @Override
    public org.springframework.security.core.userdetails.User getUserDetails(String userName, Locale locale) {
        return userMapper.toUSerDetails(userService.getUserByUserName(userName, locale));
    }

    @Override
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (Objects.nonNull(session)) {
            sessionRepository.deleteById(session.getId());
            session.invalidate();
        }
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(null);
        SecurityContextHolder.clearContext();
    }
}
