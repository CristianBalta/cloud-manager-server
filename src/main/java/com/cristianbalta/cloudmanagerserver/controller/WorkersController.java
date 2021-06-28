package com.cristianbalta.cloudmanagerserver.controller;

import com.cristianbalta.cloudmanagerserver.dto.WorkerDto;
import com.cristianbalta.cloudmanagerserver.security.jwt.JwtUtil;
import com.cristianbalta.cloudmanagerserver.service.WorkersService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workers")
public class WorkersController {

    private final WorkersService workersService;


    public WorkersController(WorkersService workersService) {
        this.workersService = workersService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createWorker(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String requestHeader,
            @RequestBody WorkerDto workerDto) {

        try {
            workerDto.setUserEmail(JwtUtil.extractUsernameFromHeader(requestHeader));
            workersService.createWorker(workerDto);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
