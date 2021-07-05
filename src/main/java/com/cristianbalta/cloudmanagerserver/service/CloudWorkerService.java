package com.cristianbalta.cloudmanagerserver.service;

import com.cristianbalta.cloudmanagerserver.dto.UserDto;
import com.cristianbalta.cloudmanagerserver.dto.WorkerBearerDto;
import com.cristianbalta.cloudmanagerserver.security.jwt.JwtUtil;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Objects;

@Service
public class CloudWorkerService {

    private final RestTemplate restTemplate;
    private final UsersService usersService;

    public CloudWorkerService(RestTemplate restTemplate, UsersService usersService) {
        this.restTemplate = restTemplate;
        this.usersService = usersService;
    }

    public String connectToRemoteWorker(String requestHeader, String workerIp) throws Exception {
        String userEmail = JwtUtil.extractUsernameFromHeader(requestHeader);

        UserDto userDto = usersService.getUserByEmail(userEmail);
        userDto.setUserPassword(usersService.getUserCredentials(userEmail).getUserPassword());

        ResponseEntity<WorkerBearerDto> responseEntity = restTemplate.exchange(
                "http://" + workerIp + ":8081/login/app",
                HttpMethod.POST,
                new HttpEntity<>(userDto),
                WorkerBearerDto.class);

        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            throw new Exception("Worker not found!");
        }

        String bearerToken = Objects.requireNonNull(responseEntity.getBody()).getWorkerBearerDto().get("bearerToken");
        usersService.saveUserWorkerBearer(userDto.getUserEmail(), bearerToken);

        return bearerToken;
    }

    public String runCommand(String requestHeader, String workerIp, String command) throws Exception {

        String userEmail = JwtUtil.extractUsernameFromHeader(requestHeader);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + usersService.getUserWorkerBearer(userEmail));

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        return restTemplate.exchange("http://" + workerIp + ":8081/api/cloud/" + command, HttpMethod.GET, entity, String.class).getBody();
    }
}
