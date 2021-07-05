package com.cristianbalta.cloudmanagerserver.controller.worker;

import com.cristianbalta.cloudmanagerserver.service.CloudWorkerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cloud-worker")
public class CloudWorkerController {

    private final CloudWorkerService cloudWorkerService;

    public CloudWorkerController(CloudWorkerService cloudWorkerService) {
        this.cloudWorkerService = cloudWorkerService;
    }

    @PostMapping
    public ResponseEntity<String> runCommand(@RequestHeader(HttpHeaders.AUTHORIZATION) String requestHeader,
                                             @RequestParam String workerIp, @RequestParam String command) throws Exception {
        return ResponseEntity.ok(cloudWorkerService.runCommand(requestHeader, workerIp, command));
    }
}
