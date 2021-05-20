package com.cristianbalta.kubeengine.controllers;

import com.cristianbalta.kubeengine.jwt.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.Collections;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginController(PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity login(@RequestParam String username, @RequestParam String password) throws NoSuchAlgorithmException {

        return ResponseEntity.ok(Collections.singletonMap("access", jwtUtil.generateToken(username)));
    }
}
