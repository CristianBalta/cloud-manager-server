package com.cristianbalta.cloudmanagerserver.controller.worker;

import com.cristianbalta.cloudmanagerserver.service.CloudWorkerService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cloud-worker-login")
public class CloudWorkerLoginController {

    private final CloudWorkerService cloudWorkerService;

    public CloudWorkerLoginController(CloudWorkerService cloudWorkerService) {
        this.cloudWorkerService = cloudWorkerService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> loginToWorker(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String requestHeader,
            @RequestParam String workerIp) throws Exception {

        String workerBearerToken = cloudWorkerService.connectToRemoteWorker(requestHeader, workerIp);
        if (workerBearerToken == null) {
            throw new Exception("Worker not owned!");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
