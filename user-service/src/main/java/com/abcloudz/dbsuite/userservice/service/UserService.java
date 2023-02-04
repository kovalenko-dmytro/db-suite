package com.abcloudz.dbsuite.userservice.service;

import com.abcloudz.dbsuite.userservice.dto.user.UserRequestDTO;
import com.abcloudz.dbsuite.userservice.dto.user.UserResponseDTO;
import com.abcloudz.dbsuite.userservice.dto.user.UserSearchRequestDTO;
import com.abcloudz.dbsuite.userservice.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Locale;

public interface UserService {

    UserResponseDTO create(UserRequestDTO userRequestDTO);
    Page<UserResponseDTO> search(Pageable pageable, UserSearchRequestDTO userSearchRequestDTO, Locale locale);
    User getUserByUserName(String name, Locale locale);
}
