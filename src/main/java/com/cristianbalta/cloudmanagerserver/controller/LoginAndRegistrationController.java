package com.cristianbalta.cloudmanagerserver.controller;

import com.cristianbalta.cloudmanagerserver.dto.UserDto;
import com.cristianbalta.cloudmanagerserver.security.jwt.JwtUtil;
import com.cristianbalta.cloudmanagerserver.service.LoginAndRegistrationService;
import com.cristianbalta.cloudmanagerserver.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(value = "/login")
public class LoginAndRegistrationController {

    private final JwtUtil jwtUtil;
    private final LoginAndRegistrationService loginAndRegistrationService;
    private final UsersService usersService;

    public LoginAndRegistrationController(JwtUtil jwtUtil,
                                          LoginAndRegistrationService loginAndRegistrationService,
                                          UsersService usersService) {
        this.jwtUtil = jwtUtil;
        this.loginAndRegistrationService = loginAndRegistrationService;
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> login(@RequestBody UserDto userDto) {
        if (loginAndRegistrationService.checkIfUserExists(userDto.getUserEmail(), userDto.getUserPassword())) {
            return ResponseEntity.ok(Collections.singletonMap("bearerToken", jwtUtil.generateToken(userDto.getUserEmail())));
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/create-user")
    public ResponseEntity<HttpStatus> createUser(@RequestBody UserDto userDto) {
        try {
            usersService.createUser(userDto);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
