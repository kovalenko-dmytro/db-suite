package com.abcloudz.springmicroservicestemplate.userservice.controller;

import com.abcloudz.springmicroservicestemplate.userservice.dto.user.UserDetailsResponseDTO;
import com.abcloudz.springmicroservicestemplate.userservice.dto.user.UserRequestDTO;
import com.abcloudz.springmicroservicestemplate.userservice.dto.user.UserResponseDTO;
import com.abcloudz.springmicroservicestemplate.userservice.dto.user.UserSearchRequestDTO;
import com.abcloudz.springmicroservicestemplate.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;
import java.util.Locale;

@Tag(name = "User Service REST API")
@RestController
@RequestMapping(value = "/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/search")
    public ResponseEntity<Page<UserResponseDTO>> search(Pageable pageable,
                                                        @RequestParam(value = "locale", required = false, defaultValue = "en") Locale locale,
                                                        @RequestBody(required = false) UserSearchRequestDTO userSearchRequestDTO) {
        return ResponseEntity.ok().body(userService.search(pageable, userSearchRequestDTO, locale));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userRequestDTO));
    }

    @Hidden
    @GetMapping(value = "/user-details")
    public ResponseEntity<UserDetailsResponseDTO> getUserDetails(@QueryParam("userName") String userName,
                                                                 @RequestParam(value = "locale", required = false, defaultValue = "en") Locale locale) {
        return ResponseEntity.ok(userService.getUserDetails(userName, locale));
    }
}
