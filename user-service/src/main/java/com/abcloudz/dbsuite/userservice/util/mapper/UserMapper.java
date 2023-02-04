package com.abcloudz.dbsuite.userservice.util.mapper;

import com.abcloudz.dbsuite.userservice.dto.auth.SignUpRequestDTO;
import com.abcloudz.dbsuite.userservice.dto.user.UserRequestDTO;
import com.abcloudz.dbsuite.userservice.dto.user.UserResponseDTO;
import com.abcloudz.dbsuite.userservice.model.Role;
import com.abcloudz.dbsuite.userservice.model.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    UserResponseDTO toUserResponseDTO(User user);
    UserRequestDTO toUserRequestDTO(SignUpRequestDTO signUpRequestDTO);

    default org.springframework.security.core.userdetails.User toUSerDetails(User user) {
        Set<GrantedAuthority> authorities = user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.getRole().name())))
            .collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(
            user.getUserName(), user.getPassword(), user.isEnabled(),
            true, true, true, authorities);
    }

    default String roleToString(Role role) {
        return (Objects.isNull(role)) ? null : role.getRole().name();
    }
}
