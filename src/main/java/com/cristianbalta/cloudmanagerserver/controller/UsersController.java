package com.cristianbalta.cloudmanagerserver.controller;

import com.cristianbalta.cloudmanagerserver.dto.UserDto;
import com.cristianbalta.cloudmanagerserver.security.jwt.JwtUtil;
import com.cristianbalta.cloudmanagerserver.service.UsersService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public ResponseEntity<UserDto> getUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws Exception {
        return ResponseEntity.ok(usersService.getUserByEmail(JwtUtil.extractUsernameFromHeader(authorizationHeader)));
    }
}
