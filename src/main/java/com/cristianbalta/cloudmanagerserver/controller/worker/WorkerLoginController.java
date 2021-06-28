package com.cristianbalta.cloudmanagerserver.controller.worker;

import com.cristianbalta.cloudmanagerserver.config.rest.RestConfig;
import com.cristianbalta.cloudmanagerserver.dto.UserDto;
import com.cristianbalta.cloudmanagerserver.security.jwt.JwtUtil;
import com.cristianbalta.cloudmanagerserver.service.UsersService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api/worker-login")
public class WorkerLoginController {

    private final RestTemplate restTemplate;
    private final RestConfig restConfig;
    private final UsersService usersService;

    public WorkerLoginController(RestTemplate restTemplate, RestConfig restConfig, UsersService usersService) {
        this.restTemplate = restTemplate;
        this.restConfig = restConfig;
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> loginToWorker(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String requestHeader,
            @RequestParam String workerIp) throws Exception {

        UserDto userDto = usersService.getUserByEmail(JwtUtil.extractUsernameFromHeader(requestHeader));

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://" + workerIp + ":8081/api/kube",
                HttpMethod.GET,
                new HttpEntity<>(null, restConfig.buildAuthHeaders(userDto)),
                String.class);

        return new ResponseEntity<>(responseEntity.getStatusCode());
    }
}
