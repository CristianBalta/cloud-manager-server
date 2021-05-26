package com.cristianbalta.cloudmanagerserver.controller;

import com.cristianbalta.cloudmanagerserver.entity.User;
import com.cristianbalta.cloudmanagerserver.entity.Worker;
import com.cristianbalta.cloudmanagerserver.security.jwt.JwtUtil;
import com.cristianbalta.cloudmanagerserver.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final LoginService loginService;

    public LoginController(PasswordEncoder passwordEncoder, JwtUtil jwtUtil, LoginService loginService) {
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        return ResponseEntity.ok(Collections.singletonMap("access", jwtUtil.generateToken(username)));
    }

    @PostMapping(path = "/create-user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(loginService.createUser(user));
    }

    @PostMapping(path = "/create-worker")
    public ResponseEntity<Worker> createWorker(@RequestBody Worker worker) {
        return ResponseEntity.ok(loginService.createWorker(worker));
    }
}
