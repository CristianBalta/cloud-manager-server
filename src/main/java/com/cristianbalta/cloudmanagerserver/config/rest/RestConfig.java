package com.cristianbalta.cloudmanagerserver.config.rest;


import com.cristianbalta.cloudmanagerserver.dto.UserDto;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class RestConfig {

    public HttpHeaders buildAuthHeaders(UserDto userDto) {
        HttpHeaders authHeaders = new HttpHeaders();
        authHeaders.add(
                "Authorization",
                "Basic " + buildEncodedCredentials(userDto.getUserEmail())
                        + userDto.getUserPassword());
        return authHeaders;
    }

    public String buildEncodedCredentials(String credentials) {
        return new String(Base64.getEncoder().encode(credentials.getBytes()));
    }
}
