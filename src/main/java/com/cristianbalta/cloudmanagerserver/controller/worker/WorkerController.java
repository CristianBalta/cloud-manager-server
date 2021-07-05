package com.cristianbalta.cloudmanagerserver.controller.worker;

import com.cristianbalta.cloudmanagerserver.security.jwt.JwtUtil;
import com.cristianbalta.cloudmanagerserver.service.WorkersService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/workers")
public class WorkerController {

    private final WorkersService workersService;

    public WorkerController(WorkersService workersService) {
        this.workersService = workersService;
    }

    @GetMapping
    public ResponseEntity<Set<String>> getWorkersByUserEmail(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws Exception {
        return ResponseEntity.ok(workersService.getWorkersByUserEmail(JwtUtil.extractUsernameFromHeader(authorizationHeader)));
    }
}
