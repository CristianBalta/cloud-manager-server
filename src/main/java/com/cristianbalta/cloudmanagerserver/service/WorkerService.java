package com.cristianbalta.cloudmanagerserver.service;

import com.cristianbalta.cloudmanagerserver.dto.UserDto;
import com.cristianbalta.cloudmanagerserver.dto.WorkerBearerDto;
import com.cristianbalta.cloudmanagerserver.security.jwt.JwtUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class WorkerService {

    private final RestTemplate restTemplate;
    private final UsersService usersService;

    public WorkerService(RestTemplate restTemplate, UsersService usersService) {
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
            throw new Exception("Worker already exists!");
        }
        return Objects.requireNonNull(responseEntity.getBody()).getWorkerBearerDto().get("bearerToken");
    }
}
