package com.cristianbalta.cloudmanagerserver.controller.worker;

import com.cristianbalta.cloudmanagerserver.dto.UserDto;
import com.cristianbalta.cloudmanagerserver.security.jwt.JwtUtil;
import com.cristianbalta.cloudmanagerserver.service.UsersService;
import com.cristianbalta.cloudmanagerserver.service.WorkerService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/worker-login")
public class WorkerLoginController {

    private final WorkerService workerService;

    public WorkerLoginController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> loginToWorker(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String requestHeader,
            @RequestParam String workerIp) throws Exception {

        String workerBearerToken = workerService.connectToRemoteWorker(requestHeader, workerIp);
        if (workerBearerToken == null) {
            throw new Exception("Worker not owned!");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
